package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.hubclientinfo.ClientInfo;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeadersBase;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.api.HubClientInfoReceiverApi;
import com.github.stevecommunity.ocpi.v221.web.api.HubClientInfoSenderApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class HubClientInfoClient extends AbstractClient implements HubClientInfoReceiverApi, HubClientInfoSenderApi {

    private static final ParameterizedTypeReference<OcpiResponse<ClientInfo>> CLIENT_INFO_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<List<ClientInfo>>> CLIENT_INFOS_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    public HubClientInfoClient(RestTemplate restTemplate, String hubClientInfoEndpointRoot, String authorizationToken) {
        super(hubClientInfoEndpointRoot, authorizationToken);
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public ResponseEntity<OcpiResponse<List<ClientInfo>>> getClientInfos(OcpiRequestHeadersBase headers,
                                                                         OcpiRequestParameters params) {
        return restTemplate.exchange(
            uri(params),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            CLIENT_INFOS_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<ClientInfo>> getClientInfo(OcpiRequestHeadersBase headers,
                                                                  String countryCode,
                                                                  String partyId) {
        return restTemplate.exchange(
            endpoint("/{country_code}/{party_id}"),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            CLIENT_INFO_RESPONSE,
            countryCode,
            partyId
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> putClientInfo(OcpiRequestHeadersBase headers,
                                                          String countryCode,
                                                          String partyId,
                                                          ClientInfo clientInfo) {
        return restTemplate.exchange(
            endpoint("/{country_code}/{party_id}"),
            HttpMethod.PUT,
            new HttpEntity<>(clientInfo, httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId
        );
    }
}
