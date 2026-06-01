package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.versions.Version;
import com.github.stevecommunity.ocpi.v221.model.versions.VersionDetails;
import com.github.stevecommunity.ocpi.v221.model.versions.types.VersionNumber;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeadersBase;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.api.VersionsApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class VersionsClient extends AbstractClient implements VersionsApi {

    private static final ParameterizedTypeReference<OcpiResponse<List<Version>>> VERSIONS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<VersionDetails>> VERSION_DETAILS_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    public VersionsClient(RestTemplate restTemplate, String versionsEndpointRoot, String authorizationToken) {
        super(versionsEndpointRoot, authorizationToken);
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public ResponseEntity<OcpiResponse<List<Version>>> getVersions(OcpiRequestHeadersBase headers) {
        return restTemplate.exchange(
            endpoint(""),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            VERSIONS_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<VersionDetails>> getVersionDetails(OcpiRequestHeadersBase headers,
                                                                          VersionNumber version) {
        return restTemplate.exchange(
            endpoint("/{version}"),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            VERSION_DETAILS_RESPONSE,
            version
        );
    }
}
