package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.cdrs.Cdr;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.api.CdrReceiverApi;
import com.github.stevecommunity.ocpi.v221.web.api.CdrSenderApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class CdrClient extends AbstractClient implements CdrReceiverApi, CdrSenderApi {

    private static final ParameterizedTypeReference<OcpiResponse<Cdr>> CDR_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<List<Cdr>>> CDRS_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    public CdrClient(RestTemplate restTemplate, String cdrEndpointRoot, String authorizationToken) {
        super(cdrEndpointRoot, authorizationToken);
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public ResponseEntity<OcpiResponse<List<Cdr>>> getCdrs(OcpiRequestHeaders headers, OcpiRequestParameters params) {
        return restTemplate.exchange(
            uri(params),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            CDRS_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Cdr>> getCdr(OcpiRequestHeaders headers, String cdrId) {
        return restTemplate.exchange(
            endpoint("/{cdr_id}"),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            CDR_RESPONSE,
            cdrId
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> postCdr(OcpiRequestHeaders headers, Cdr cdr) {
        return restTemplate.exchange(
            endpoint(""),
            HttpMethod.POST,
            new HttpEntity<>(cdr, httpHeaders(headers)),
            OcpiResponseVoid.class
        );
    }

}
