package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.sessions.ChargingPreferences;
import com.github.stevecommunity.ocpi.v221.model.sessions.Session;
import com.github.stevecommunity.ocpi.v221.model.sessions.SessionPatch;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.ChargingPreferencesResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.api.SessionsReceiverApi;
import com.github.stevecommunity.ocpi.v221.web.api.SessionsSenderApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class SessionsClient extends AbstractClient implements SessionsReceiverApi, SessionsSenderApi {

    private static final String SESSION_PATH = "/{country_code}/{party_id}/{session_id}";

    private static final ParameterizedTypeReference<OcpiResponse<Session>> SESSION_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<List<Session>>> SESSIONS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<ChargingPreferencesResponse>> CHARGING_PREFERENCES_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    public SessionsClient(RestTemplate restTemplate, String sessionsEndpointRoot, String authorizationToken) {
        super(sessionsEndpointRoot, authorizationToken);
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public ResponseEntity<OcpiResponse<List<Session>>> getSessions(OcpiRequestHeaders headers, OcpiRequestParameters params) {
        return restTemplate.exchange(
            uri(params),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            SESSIONS_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Session>> getSession(OcpiRequestHeaders headers,
                                                            String countryCode,
                                                            String partyId,
                                                            String sessionId) {
        return restTemplate.exchange(
            endpoint(SESSION_PATH),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            SESSION_RESPONSE,
            countryCode,
            partyId,
            sessionId
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> putSession(OcpiRequestHeaders headers,
                                                       String countryCode,
                                                       String partyId,
                                                       String sessionId,
                                                       Session session) {
        return restTemplate.exchange(
            endpoint(SESSION_PATH),
            HttpMethod.PUT,
            new HttpEntity<>(session, httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId,
            sessionId
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> patchSession(OcpiRequestHeaders headers,
                                                         String countryCode,
                                                         String partyId,
                                                         String sessionId,
                                                         SessionPatch session) {
        return restTemplate.exchange(
            endpoint(SESSION_PATH),
            HttpMethod.PATCH,
            new HttpEntity<>(session, httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId,
            sessionId
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<ChargingPreferencesResponse>> putChargingPreferences(OcpiRequestHeaders headers,
                                                                                            String sessionId,
                                                                                            ChargingPreferences chargingPreferences) {
        return restTemplate.exchange(
            endpoint("/{session_id}/charging_preferences"),
            HttpMethod.PUT,
            new HttpEntity<>(chargingPreferences, httpHeaders(headers)),
            CHARGING_PREFERENCES_RESPONSE,
            sessionId
        );
    }

}
