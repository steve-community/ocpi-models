package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.credentials.Credentials;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeadersBase;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.api.CredentialsApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class CredentialsClient extends AbstractClient implements CredentialsApi {

    private static final ParameterizedTypeReference<OcpiResponse<Credentials>> CREDENTIALS_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    public CredentialsClient(RestTemplate restTemplate, String credentialsEndpointRoot, String authorizationToken) {
        super(credentialsEndpointRoot, authorizationToken);
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public ResponseEntity<OcpiResponse<Credentials>> getCredentials(OcpiRequestHeadersBase headers) {
        return restTemplate.exchange(
            endpoint(""),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            CREDENTIALS_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Credentials>> postCredentials(OcpiRequestHeadersBase headers, Credentials credentials) {
        return restTemplate.exchange(
            endpoint(""),
            HttpMethod.POST,
            new HttpEntity<>(credentials, httpHeaders(headers)),
            CREDENTIALS_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Credentials>> putCredentials(OcpiRequestHeadersBase headers, Credentials credentials) {
        return restTemplate.exchange(
            endpoint(""),
            HttpMethod.PUT,
            new HttpEntity<>(credentials, httpHeaders(headers)),
            CREDENTIALS_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> deleteCredentials(OcpiRequestHeadersBase headers) {
        return restTemplate.exchange(
            endpoint(""),
            HttpMethod.DELETE,
            new HttpEntity<>(httpHeaders(headers)),
            OcpiResponseVoid.class
        );
    }
}
