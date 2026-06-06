# Design Decisions

## 1. Dense and Compact Data Modeling

> To keep the Java and wire representations close together, models intentionally use snake_case property names that match OCPI JSON fields instead of standard Java camelCase names.

<details>

<summary>With conventional Java style, the tariff model would look like this:</summary>

```java
@Data
public class Tariff {

    @NotEmpty
    @Size(max = 2)
    @JsonProperty("country_code")
    private String countryCode;

    @NotEmpty
    @Size(max = 3)
    @JsonProperty("party_id")
    private String partyId;

    @NotEmpty
    @Size(max = 36)
    @JsonProperty("id")
    private String id;

    @NotEmpty
    @Size(max = 3)
    @JsonProperty("currency")
    private String currency;

    @JsonProperty("type")
    private TariffType type;

    @Valid
    @JsonProperty("tariff_alt_text")
    private List<DisplayText> tariffAltText;

    @Size(max = 255)
    @JsonProperty("tariff_alt_url")
    private String tariffAltUrl;

    @Valid
    @JsonProperty("min_price")
    private Price minPrice;

    @Valid
    @JsonProperty("max_price")
    private Price maxPrice;

    @NotEmpty
    @Valid
    @JsonProperty("elements")
    private List<TariffElement> elements;

    @JsonProperty("start_date_time")
    private Instant startDateTime;

    @JsonProperty("end_date_time")
    private Instant endDateTime;

    @Valid
    @JsonProperty("energy_mix")
    private EnergyMix energyMix;

    @NotNull
    @JsonProperty("last_updated")
    private Instant lastUpdated;
}
```

Even with Lombok keeping getters, setters, and other boilerplate out of the source, the model becomes harder to scan.
The annotations carry important wire-format information, but they also spread the OCPI field names away from the field declarations themselves.
Using OCPI-style property names directly keeps the model denser and makes the JSON shape visible at a glance.

</details>

## 2. OCPI Request Headers Are Bundled Into One POJO

`OcpiRequestHeadersBase` contains the headers required on all OCPI requests:

```java
@Getter
@ToString
public class OcpiRequestHeadersBase {
    @NotEmpty String xRequestId;
    @NotEmpty String xCorrelationId;
}
```

`OcpiRequestHeaders` extends that base type with the optional OCPI routing headers:

```java
@Getter
@ToString
public class OcpiRequestHeaders extends OcpiRequestHeadersBase {
    @Size(min = 2, max = 2) String fromCountryCode;
    @Size(min = 3, max = 3) String fromPartyId;
    @Size(min = 2, max = 2) String toCountryCode;
    @Size(min = 3, max = 3) String toPartyId;
}
```

Both types are created and populated by [OcpiRequestHeadersArgumentResolver](src/main/java/com/github/stevecommunity/ocpi/config/OcpiRequestHeadersArgumentResolver.java).
API interfaces receive the relevant header object as a method parameter:

```java
@Parameter(hidden = true) @Valid OcpiRequestHeaders headers
```

Controller implementations can then work with one compact object instead of a long list of individual header parameters.
`@Parameter(hidden = true)` hides this Java object from OpenAPI discovery, because the actual HTTP headers are declared programmatically in [OcpiAutoConfiguration](src/main/java/com/github/stevecommunity/ocpi/config/OcpiAutoConfiguration.java#L49).
`@Valid` activates Jakarta/Hibernate validation for the header object.

## 3. OCPI Pagination Parameters Are Bundled Into One POJO

`OcpiRequestParameters` bundles the common OCPI pagination request parameters:

```java
@Getter
@Setter
@ToString
public class OcpiRequestParameters {
    
    public static final int DEFAULT_LIMIT = 50;
    public static final int MAX_LIMIT = 200;
    
    Instant date_from;
    Instant date_to;
    @Min(value = 0) Integer offset = 0;
    @Min(value = 0) Integer limit = DEFAULT_LIMIT;
}
```

API interfaces receive this object as a method parameter:

```java
@Valid @ParameterObject OcpiRequestParameters params
```

`@ParameterObject` lets OpenAPI discover the fields as HTTP request parameters even though Java code sees a single object.
`@Valid` activates Jakarta/Hibernate validation for the pagination object.
`getLimit()` returns the effective page size: missing limits use `DEFAULT_LIMIT`, and requested limits above `MAX_LIMIT` are capped.

---

Together, these two bundling decisions let endpoint signatures stay compact:

```java
@GetMapping
default ResponseEntity<OcpiResponse<List<Cdr>>> getCdrs(
    @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
    @Valid @ParameterObject OcpiRequestParameters params
) {
    // ...
}
```

With conventional Java/Spring MVC style, the same endpoint would look more like this:

```java
@GetMapping
default ResponseEntity<OcpiResponse<List<Cdr>>> getCdrs(
    @RequestHeader("X-Request-ID") String xRequestId,
    @RequestHeader("X-Correlation-ID") String xCorrelationId,
    @RequestHeader("OCPI-from-country-code", required = false) String fromCountryCode,
    @RequestHeader("OCPI-from-party-id", required = false) String fromPartyId,
    @RequestHeader("OCPI-to-country-code", required = false) String toCountryCode,
    @RequestHeader("OCPI-to-party-id", required = false) String toPartyId,
    @RequestParam(value = "date_from", required = false) Instant dateFrom,
    @RequestParam(value = "date_to", required = false) Instant dateTo,
    @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
    @RequestParam(value = "limit", required = false, defaultValue = "50") Integer limit
) {
    // ...
}
```

The behavior is similar, but the API surface is much noisier because every header and request parameter becomes part of the endpoint signature.
Now imagine that level of method-signature verbosity repeated across all OCPI endpoints.

## 4. PUT and PATCH Models Share Shape but Not Null Checks

OCPI often defines the same object shape for full replacement and partial update endpoints.
A `PUT` request must carry a complete object, while a `PATCH` request should only carry the fields that are changing.
That means most field constraints, such as `@Size`, nested `@Valid`, enum types, and `last_updated`, should be shared.
The required-field null checks, however, are different.

For example, [LocationPatch](src/main/java/com/github/stevecommunity/ocpi/v221/model/locations/LocationPatch.java) can reuse the full location field set while keeping most fields optional:

```java
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LocationPatch extends AbstractLocation {
    @Size(max = 2) String country_code;
    @Size(max = 3) String party_id;
    Boolean publish;
    @Valid List<PublishTokenType> publish_allowed_to;
    @Valid List<AdditionalGeoLocation> related_locations;
    ParkingType parking_type;
    @Valid List<Evse> evses;
    @Valid List<DisplayText> directions;
    @Valid BusinessDetails operator;
    @Valid BusinessDetails suboperator;
    @Valid BusinessDetails owner;
    List<Facility> facilities;
    @Size(max = 255) String time_zone;
    @Valid Hours opening_times;
    Boolean charging_when_closed;
    @Valid List<Image> images;
    @Valid EnergyMix energy_mix;
    @NotNull Instant last_updated;
}
```

The [LocationNullChecks](src/main/java/com/github/stevecommunity/ocpi/v221/model/locations/LocationNullChecks.java) interface defines the required-field constraints needed by the full object:

```java
public interface LocationNullChecks extends AbstractLocationNullChecks {
    @NotEmpty String getCountry_code();
    @NotEmpty String getParty_id();
    @NotNull Boolean getPublish();
    @NotEmpty String getTime_zone();
}
```

The full [Location](src/main/java/com/github/stevecommunity/ocpi/v221/model/locations/Location.java) model then composes the patch shape with those required-field constraints:

```java
public class Location extends LocationPatch implements LocationNullChecks {
}
```

This keeps the PATCH model permissive without duplicating the whole object model.
It also keeps the PUT model strict without requiring validation groups on every endpoint.
The approach remains compatible with Jakarta validation because constraints declared on interface getters are part of the Bean Validation model.
It is also friendly to OpenAPI generation because the public full-object type still has a concrete Java class, while the patch type remains a separate concrete Java class.
The same pattern is used for [Location](src/main/java/com/github/stevecommunity/ocpi/v221/model/locations/Location.java), [Evse](src/main/java/com/github/stevecommunity/ocpi/v221/model/locations/Evse.java), [Connector](src/main/java/com/github/stevecommunity/ocpi/v221/model/locations/Connector.java), [Session](src/main/java/com/github/stevecommunity/ocpi/v221/model/sessions/Session.java), and [Token](src/main/java/com/github/stevecommunity/ocpi/v221/model/tokens/Token.java).
