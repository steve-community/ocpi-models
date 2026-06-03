package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.versions.types.InterfaceRole;
import com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeadersBase;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_OCPI_FROM_COUNTRY;
import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_OCPI_FROM_PARTY_ID;
import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_OCPI_TO_COUNTRY;
import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_OCPI_TO_PARTY_ID;
import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_X_CORRELATION_ID;
import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_X_REQUEST_ID;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public abstract class AbstractClient {

    private final String endpointRoot;
    private final String authorizationToken;

    protected AbstractClient(String endpointRoot, String authorizationToken) {
        this.endpointRoot = normalizeEndpointRoot(endpointRoot);
        this.authorizationToken = normalizeAuthorizationToken(authorizationToken);
    }

    public ModuleID getId() {
        return null;
    }

    public String getPath() {
        return null;
    }

    public InterfaceRole getRole() {
        return null;
    }

    protected HttpHeaders httpHeaders(OcpiRequestHeadersBase ocpiHeaders) {
        Objects.requireNonNull(ocpiHeaders, "ocpiHeaders must not be null");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(AUTHORIZATION, authorizationToken);

        addIfPresent(headers, HEADER_X_REQUEST_ID, ocpiHeaders.getXRequestId());
        addIfPresent(headers, HEADER_X_CORRELATION_ID, ocpiHeaders.getXCorrelationId());

        if (ocpiHeaders instanceof OcpiRequestHeaders routingHeaders) {
            addIfPresent(headers, HEADER_OCPI_FROM_COUNTRY, routingHeaders.getFromCountryCode());
            addIfPresent(headers, HEADER_OCPI_FROM_PARTY_ID, routingHeaders.getFromPartyId());
            addIfPresent(headers, HEADER_OCPI_TO_COUNTRY, routingHeaders.getToCountryCode());
            addIfPresent(headers, HEADER_OCPI_TO_PARTY_ID, routingHeaders.getToPartyId());
        }

        return headers;
    }

    protected String endpoint(String path) {
        return endpointRoot + path;
    }

    protected static void addIfPresent(HttpHeaders headers, String name, String value) {
        if (value != null) {
            headers.set(name, value);
        }
    }

    protected static String normalizeEndpointRoot(String endpointRoot) {
        Objects.requireNonNull(endpointRoot, "endpointRoot must not be null");

        if (endpointRoot.isBlank()) {
            throw new IllegalArgumentException("endpointRoot must not be blank");
        }

        String normalized = endpointRoot;
        while (normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }

    private static String normalizeAuthorizationToken(String authorizationToken) {
        Objects.requireNonNull(authorizationToken, "authorizationToken must not be null");

        if (authorizationToken.isBlank()) {
            throw new IllegalArgumentException("authorizationToken must not be blank");
        }

        return authorizationToken.startsWith("Token ")
            ? authorizationToken
            : "Token " + authorizationToken;
    }

    protected URI uri(OcpiRequestParameters params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint(""));

        if (params != null) {
            addIfPresent(builder, "date_from", params.getDate_from());
            addIfPresent(builder, "date_to", params.getDate_to());
            addIfPresent(builder, "offset", params.getOffset());
            addIfPresent(builder, "limit", params.getLimit());
        }

        return builder.build().toUri();
    }

    protected static void addIfPresent(UriComponentsBuilder builder, String name, Object value) {
        if (value != null) {
            builder.queryParam(name, value);
        }
    }
}
