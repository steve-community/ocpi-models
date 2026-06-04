package com.github.stevecommunity.ocpi.v221.web.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_OCPI_FROM_COUNTRY;
import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_OCPI_FROM_PARTY_ID;
import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_OCPI_TO_COUNTRY;
import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_OCPI_TO_PARTY_ID;
import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_X_CORRELATION_ID;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class OcpiClientBuilder {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    private OcpiClientBuilder(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
    }

    /**
     * Creates a client builder for one partner credential.
     * Use a separate OcpiClient for each partner platform/token combination.
     */
    public static OcpiClientBuilder create(RestTemplate restTemplate, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, normalizeAuthorizationToken(token));
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new OcpiClientBuilder(restTemplate, headers);
    }

    /**
     * Sets the OCPI sender identity, usually our country code and party ID.
     * A client can be reused while this identity stays the same.
     */
    public OcpiClientBuilder from(String fromCountryCode, String fromPartyId) {
        addIfPresent(headers, HEADER_OCPI_FROM_COUNTRY, fromCountryCode);
        addIfPresent(headers, HEADER_OCPI_FROM_PARTY_ID, fromPartyId);
        return this;
    }

    /**
     * Sets the OCPI receiver identity.
     * A client can be reused while calls target the same partner party.
     */
    public OcpiClientBuilder to(String toCountryCode, String toPartyId) {
        addIfPresent(headers, HEADER_OCPI_TO_COUNTRY, toCountryCode);
        addIfPresent(headers, HEADER_OCPI_TO_PARTY_ID, toPartyId);
        return this;
    }

    /**
     * Sets a fixed correlation ID for all requests sent by the created client.
     * If unset, the client generates a fresh correlation ID per request.
     * Use this only when the client instance represents one logical correlation context.
     */
    public OcpiClientBuilder correlationId(String correlationId) {
        addIfPresent(headers, HEADER_X_CORRELATION_ID, correlationId);
        return this;
    }

    public OcpiClient build() {
        return new OcpiClient(this.restTemplate, this.headers);
    }

    private static void addIfPresent(HttpHeaders headers, String name, String value) {
        if (value != null) {
            headers.set(name, value);
        }
    }

    private static String normalizeAuthorizationToken(String authorizationToken) {
        Objects.requireNonNull(authorizationToken, "authorizationToken must not be null");

        if (authorizationToken.isBlank()) {
            throw new IllegalArgumentException("authorizationToken must not be blank");
        }

        String credentialsToken = authorizationToken.startsWith("Token ")
            ? authorizationToken.substring("Token ".length())
            : authorizationToken;

        String encodedToken = Base64.getEncoder().encodeToString(credentialsToken.getBytes(StandardCharsets.UTF_8));
        return "Token " + encodedToken;
    }
}
