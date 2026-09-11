# ocpi-models

Java data model, Spring MVC API mappings and RestTemplate clients for OCPI 2.2.1.

The library provides models and endpoint contracts; the consuming application implements module behavior, persistence, and authorization.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
  - [Build from source](#build-from-source)
  - [Use a published package](#use-a-published-package)
- [Module Coverage](#module-coverage)
- [Examples](#examples)
  - [Data model](#data-model)
  - [Server-side APIs](#server-side-apis)
  - [API clients](#api-clients)
- [Spring Integration](#spring-integration)
- [Design Goals](#design-goals)
- [Motivation](#motivation)
  - [OpenAPI-first projects](#1-openapi-first-projects)
  - [Implementation-first projects](#2-implementation-first-projects)

## Requirements

The current [POM](pom.xml) and [CI workflow](.github/workflows/main.yml) use the following baseline:

| Component    | Requirement or current dependency                                                 |
|--------------|-----------------------------------------------------------------------------------|
| Java         | JDK 21 to build; Java 21 or later to run                                          |
| Build tool   | Maven, installed separately; no Maven wrapper is included                         |
| Foundation   | Spring MVC                                                                        |
| Dependencies | OpenAPI integration, Jackson 3, Jakarta Validation API, Lombok                    |

The Spring integration targets applications on the Spring Boot 4 / Spring Framework 7 generation.
Hibernate Validator 9.1.0.Final is used in tests only, so applications must provide their own validation implementation, for example through Spring Boot's validation starter.

## Installation

### Build from source

Install the current checkout into your local Maven repository:

```bash
git clone https://github.com/steve-community/ocpi-models.git
cd ocpi-models
mvn clean install
```

Then add this dependency to your application's `pom.xml`:

```xml
<dependency>
    <groupId>com.github.steve-community</groupId>
    <artifactId>ocpi-models</artifactId>
    <version>0.0.4-SNAPSHOT</version>
</dependency>
```

This version matches the current source POM. If you build a different tag or checkout, use that POM's version.
A local installation does not require GitHub Packages credentials.

### Use a published package

The project is configured to publish to GitHub Packages.
Choose an available version from the repository's [Packages page](https://github.com/steve-community/ocpi-models/packages) and use it in the dependency above; the current development snapshot is not necessarily published.

Add this repository inside your application's `<repositories>` element:

```xml
<repository>
    <id>ocpi-models-github</id>
    <url>https://maven.pkg.github.com/steve-community/ocpi-models</url>
</repository>
```

GitHub Packages requires authentication even for public Maven packages.
Set `GITHUB_USERNAME` and `GITHUB_TOKEN` in your environment, using a personal access token (classic) with `read:packages` access, and merge this server entry into `~/.m2/settings.xml`:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0">
    <servers>
        <server>
            <id>ocpi-models-github</id>
            <username>${env.GITHUB_USERNAME}</username>
            <password>${env.GITHUB_TOKEN}</password>
        </server>
    </servers>
</settings>
```

The repository and server IDs must match.
If you select a published snapshot, add `<snapshots><enabled>true</enabled></snapshots>` inside the repository entry.
See [GitHub's Maven registry documentation](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry) for authentication details.

## Module Coverage

The following OCPI 2.2.1 models, Spring MVC interfaces, and client methods are present in this repository.
“Available” describes library building blocks, not a running module implementation or a claim of protocol certification.

| Module / discovery API | Models | Sender API | Receiver API | Client methods |
| --- | --- | --- | --- | --- |
| Versions | ✅ | Shared `VersionsApi` | Shared `VersionsApi` | ✅ |
| Credentials | ✅ | Shared `CredentialsApi` | Shared `CredentialsApi` | ✅ |
| Locations | ✅ | `LocationsSenderApi` | `LocationsReceiverApi` | ✅ |
| Sessions | ✅ | `SessionsSenderApi` | `SessionsReceiverApi` | ✅ |
| CDRs | ✅ | `CdrsSenderApi` | `CdrsReceiverApi` | ✅ |
| Tokens | ✅ | `TokensSenderApi` | `TokensReceiverApi` | ✅ |
| Tariffs | ✅ | `TariffsSenderApi` | `TariffsReceiverApi` | ✅ |
| Commands | ✅ | `CommandsSenderApi` | `CommandsReceiverApi` | ✅ |
| Charging Profiles | ✅ | `ChargingProfilesSenderApi` | `ChargingProfilesReceiverApi` | ✅ |
| Hub Client Info | ✅ | `HubClientInfoSenderApi` | `HubClientInfoReceiverApi` | ✅ |

Sender and receiver refer to the OCPI interface roles; Versions and Credentials each use one shared interface.
Commands and Charging Profiles include result callback contracts and client methods.
Browse the [models](src/main/java/com/github/stevecommunity/ocpi/v221/model), [API interfaces](src/main/java/com/github/stevecommunity/ocpi/v221/web/api), and [client methods](src/main/java/com/github/stevecommunity/ocpi/v221/web/client/OcpiClient.java) for individual operations.

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
    List<@Valid DisplayText> tariff_alt_text;
    @Size(max = 255) String tariff_alt_url;
    @Valid Price min_price;
    @Valid Price max_price;
    @NotEmpty List<@Valid TariffElement> elements;
    @OcpiDateTime Instant start_date_time;
    @OcpiDateTime Instant end_date_time;
    @Valid EnergyMix energy_mix;
    @OcpiDateTime @NotNull Instant last_updated;
}
```

Data model classes can be found in package `com.github.stevecommunity.ocpi.v221.model`.

### Server-side APIs

The sender interface for the Tariffs module stays similarly compact:

```java
@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = OcpiApi.TARIFFS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public interface TariffsSenderApi extends OcpiApi.Tariffs.Sender {

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

The client side uses one compact `OcpiClient` around `RestTemplate`.
It wraps OCPI authorization, request/correlation ID generation, routing headers, response unwrapping, and pagination/query plumbing.
Client methods expect the full module endpoint root discovered through OCPI versions/version-details, not just the remote system root.
Callback-style methods that receive a URL from the remote party expect that complete URL directly.

```java
RestTemplate restTemplate = new RestTemplate();

OcpiClient client = OcpiClientBuilder
    .create(restTemplate, "my-ocpi-token")
    .from("DE", "MSP")
    .to("DE", "CPO")
    .build();

Cdr cdr = client.getCdr("https://cpo.example.com/ocpi/2.2.1/cdrs", "cdr-123");
```

Client classes can be found in package `com.github.stevecommunity.ocpi.v221.web.client`.

## Spring Integration

Spring Boot discovers [OcpiAutoConfiguration](src/main/java/com/github/stevecommunity/ocpi/config/OcpiAutoConfiguration.java) through the library's `AutoConfiguration.imports` registration.
It activates only in servlet web applications and registers the OCPI header resolver, version-number converter, date formatter, and OpenAPI group.

Implement the relevant API interfaces in your application's `@RestController` classes and keep those controllers within your application's component scan.

[OcpiDateTime](src/main/java/com/github/stevecommunity/ocpi/v221/util/OcpiDateTime.java) supplies Jackson 3 deserialization for annotated `Instant` properties, while auto-configuration registers the corresponding Spring MVC formatter for request parameters.

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
For more detail on these tradeoffs, see [README-DESIGN.md](README-DESIGN.md).

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

---

This library does not try to protect implementors from every possible mistake.
It provides compact, accurate building blocks for OCPI models and endpoint contracts, while leaving application-level decisions, persistence, authorization, business rules, and operational safeguards to the consuming application.
