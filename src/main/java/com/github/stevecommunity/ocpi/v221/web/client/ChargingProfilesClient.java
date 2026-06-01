package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ActiveChargingProfileResult;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ChargingProfileResponse;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ChargingProfileResult;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ClearProfileResult;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.SetChargingProfile;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types.ActiveChargingProfile;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.api.ChargingProfilesReceiverApi;
import com.github.stevecommunity.ocpi.v221.web.api.ChargingProfilesSenderApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

public class ChargingProfilesClient extends AbstractClient implements ChargingProfilesReceiverApi, ChargingProfilesSenderApi {

    private static final ParameterizedTypeReference<OcpiResponse<ChargingProfileResponse>> CHARGING_PROFILE_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    public ChargingProfilesClient(RestTemplate restTemplate, String chargingProfilesEndpointRoot, String authorizationToken) {
        super(chargingProfilesEndpointRoot, authorizationToken);
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public ResponseEntity<OcpiResponse<ChargingProfileResponse>> getActiveChargingProfile(OcpiRequestHeaders headers,
                                                                                          String sessionId,
                                                                                          Integer duration,
                                                                                          String responseUrl) {
        return restTemplate.exchange(
            uri("/{session_id}", sessionId, duration, responseUrl),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            CHARGING_PROFILE_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<ChargingProfileResponse>> putChargingProfile(OcpiRequestHeaders headers,
                                                                                    String sessionId,
                                                                                    SetChargingProfile chargingProfile) {
        return restTemplate.exchange(
            endpoint("/{session_id}"),
            HttpMethod.PUT,
            new HttpEntity<>(chargingProfile, httpHeaders(headers)),
            CHARGING_PROFILE_RESPONSE,
            sessionId
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<ChargingProfileResponse>> deleteChargingProfile(OcpiRequestHeaders headers,
                                                                                       String sessionId,
                                                                                       String responseUrl) {
        return restTemplate.exchange(
            uri("/{session_id}", sessionId, null, responseUrl),
            HttpMethod.DELETE,
            new HttpEntity<>(httpHeaders(headers)),
            CHARGING_PROFILE_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> postActiveChargingProfileResult(OcpiRequestHeaders headers,
                                                                            ActiveChargingProfileResult result) {
        return postVoid(headers, "/response/active", result);
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> postChargingProfileResult(OcpiRequestHeaders headers,
                                                                      ChargingProfileResult result) {
        return postVoid(headers, "/response/set", result);
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> postClearProfileResult(OcpiRequestHeaders headers,
                                                                   ClearProfileResult result) {
        return postVoid(headers, "/response/clear", result);
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> putActiveChargingProfile(OcpiRequestHeaders headers,
                                                                     String sessionId,
                                                                     ActiveChargingProfile activeChargingProfile) {
        return restTemplate.exchange(
            endpoint("/{session_id}/active"),
            HttpMethod.PUT,
            new HttpEntity<>(activeChargingProfile, httpHeaders(headers)),
            OcpiResponseVoid.class,
            sessionId
        );
    }

    private ResponseEntity<OcpiResponseVoid> postVoid(OcpiRequestHeaders headers, String path, Object body) {
        return restTemplate.exchange(
            endpoint(path),
            HttpMethod.POST,
            new HttpEntity<>(body, httpHeaders(headers)),
            OcpiResponseVoid.class
        );
    }

    private URI uri(String path, String sessionId, Integer duration, String responseUrl) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint(path));
        addIfPresent(builder, "duration", duration);
        addIfPresent(builder, "response_url", responseUrl);
        return builder.build(sessionId);
    }
}
