package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.tariffs.Tariff;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.api.TariffsReceiverApi;
import com.github.stevecommunity.ocpi.v221.web.api.TariffsSenderApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class TariffsClient extends AbstractClient implements TariffsReceiverApi, TariffsSenderApi {

    private static final String TARIFF_PATH = "/{country_code}/{party_id}/{tariff_id}";

    private static final ParameterizedTypeReference<OcpiResponse<Tariff>> TARIFF_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<List<Tariff>>> TARIFFS_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    public TariffsClient(RestTemplate restTemplate, String tariffsEndpointRoot, String authorizationToken) {
        super(tariffsEndpointRoot, authorizationToken);
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public ResponseEntity<OcpiResponse<List<Tariff>>> getTariffs(OcpiRequestHeaders headers, OcpiRequestParameters params) {
        return restTemplate.exchange(
            uri(params),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            TARIFFS_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Tariff>> getTariff(OcpiRequestHeaders headers,
                                                          String countryCode,
                                                          String partyId,
                                                          String tariffId) {
        return restTemplate.exchange(
            endpoint(TARIFF_PATH),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            TARIFF_RESPONSE,
            countryCode,
            partyId,
            tariffId
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> putTariff(OcpiRequestHeaders headers,
                                                      String countryCode,
                                                      String partyId,
                                                      String tariffId,
                                                      Tariff tariff) {
        return restTemplate.exchange(
            endpoint(TARIFF_PATH),
            HttpMethod.PUT,
            new HttpEntity<>(tariff, httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId,
            tariffId
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> deleteTariff(OcpiRequestHeaders headers,
                                                         String countryCode,
                                                         String partyId,
                                                         String tariffId) {
        return restTemplate.exchange(
            endpoint(TARIFF_PATH),
            HttpMethod.DELETE,
            new HttpEntity<>(httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId,
            tariffId
        );
    }
}
