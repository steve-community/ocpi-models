# ocpi-models

Java data model, Spring MVC API mappings and RestTemplate clients for OCPI 2.2.1.

## Table of Contents

- [Motivation](#motivation)
  - [OpenAPI-first projects](#1-openapi-first-projects)
  - [Implementation-first projects](#2-implementation-first-projects)
- [Design Goals](#design-goals)
- [Examples](#examples)
  - [Data model](#data-model)
  - [Server-side APIs](#server-side-apis)
  - [API clients](#api-clients)

## Motivation

This library exists mostly for better developer experience and ergonomics.

Many OCPI projects already exist, but most fall into one of two groups:

1. Attempts at an OpenAPI specification.
2. Direct language-specific models and implementations.

Both approaches can be useful.
Both also created friction here.

### 1. OpenAPI-first projects

Even before asking whether an OpenAPI document is correct and complete with respect to the official OCPI specification, the generated Java code tends to be noisy and verbose.

Example:
OCPI defines request headers that appear on many endpoints.
Generated controller interfaces usually expose each header as a separate method parameter on every endpoint.
The result is cumbersome, repetitive, and hard to scan.

The same problem appears in the core domain models.
Generated Java models are often flat and repetitive, with little code sharing, inheritance, or hand-tuned structure.
It proved hard to configure the generator enough to make the generated core compact and maintainable.

### 2. Implementation-first projects

Existing Java implementations often make questionable data modeling choices or strong assumptions about application structure.
Some feel more like frameworks than model/API libraries: useful when their assumptions match your application, awkward when they do not.

Validation annotations and validation boundary decisions can be just as problematic, especially when PATCH semantics, nested objects, and required OCPI strings are modeled too loosely or too aggressively.

## Design Goals

This project stays closer to a library: compact OCPI models and endpoint contracts, while application behavior and module implementation choices stay with the consuming Spring Boot application.

1. Dense, compact data modeling.
To keep the Java and wire representations close together, models intentionally use snake_case property names that match OCPI JSON fields instead of standard Java camelCase names.
Lombok hides the boilerplate.
2. OCPI request headers are bundled into one POJO and injected into endpoint methods.
3. OCPI pagination parameters are bundled into one POJO and injected into endpoint methods.
4. Each OCPI module exposes Java interfaces for the relevant API endpoints.
Applications choose which role/module interfaces to implement.

The goal is to keep software code plumbing out of application code where a compact library abstraction can carry it instead.

## Examples

### Data model

For example, `Tariff` is modeled like this:

```java
@Data
public class Tariff {
    @NotEmpty @Size(max = 2) String country_code;
    @NotEmpty @Size(max = 3) String party_id;
    @NotEmpty @Size(max = 36) String id;
    @NotEmpty @Size(max = 3) String currency;
    TariffType type;
    @Valid List<DisplayText> tariff_alt_text;
    @Size(max = 255) String tariff_alt_url;
    @Valid Price min_price;
    @Valid Price max_price;
    @NotEmpty @Valid List<TariffElement> elements;
    Instant start_date_time;
    Instant end_date_time;
    @Valid EnergyMix energy_mix;
    @NotNull Instant last_updated;
}
```

Data model classes can be found in package `com.github.stevecommunity.ocpi.v221.model`.

### Server-side APIs

The sender interface for the Tariffs module stays similarly compact:

```java
@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = "/ocpi/2.2.1/tariffs", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TariffsSenderApi {

    @GetMapping
    default ResponseEntity<OcpiResponse<List<Tariff>>> getTariffs(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @Valid @ParameterObject OcpiRequestParameters params
    ) {
        throw new RuntimeException("Not implemented");
    }
}
```

An application can implement only the API it needs:

```java
@RestController
public class TariffSenderController implements TariffsSenderApi {

    @Override
    public ResponseEntity<OcpiResponse<List<Tariff>>> getTariffs(OcpiRequestHeaders headers,
                                                                 OcpiRequestParameters params) {
        // TODO
    }
}
```

API interfaces can be found in package `com.github.stevecommunity.ocpi.v221.web.api`.

### API clients

The same API contracts can also be used from the client side.
Client classes wrap `RestTemplate`, OCPI authorization, OCPI request headers, and pagination/query plumbing.
Each client expects the full module endpoint root discovered through OCPI versions/version-details, not just the remote system root.

```java
RestTemplate restTemplate = new RestTemplate();

CdrClient cdrClient = new CdrClient(
    restTemplate,
    "https://cpo.example.com/ocpi/2.2.1/cdrs",
    "my-ocpi-token"
);

OcpiRequestHeaders headers = OcpiRequestHeaders.builder()
    .xRequestId(UUID.randomUUID().toString())
    .xCorrelationId(UUID.randomUUID().toString())
    .fromCountryCode("DE")
    .fromPartyId("MSP")
    .toCountryCode("DE")
    .toPartyId("CPO")
    .build();

ResponseEntity<OcpiResponse<Cdr>> response = cdrClient.getCdr(headers, "cdr-123");
```

Client classes can be found in package `com.github.stevecommunity.ocpi.v221.web.client`.

---

This library does not try to protect implementors from every possible mistake.
It provides compact, accurate building blocks for OCPI models and endpoint contracts, while leaving application-level decisions, persistence, authorization, business rules, and operational safeguards to the consuming application.
