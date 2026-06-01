package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.locations.Connector;
import com.github.stevecommunity.ocpi.v221.model.locations.ConnectorPatch;
import com.github.stevecommunity.ocpi.v221.model.locations.Evse;
import com.github.stevecommunity.ocpi.v221.model.locations.EvsePatch;
import com.github.stevecommunity.ocpi.v221.model.locations.Location;
import com.github.stevecommunity.ocpi.v221.model.locations.LocationPatch;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.api.LocationReceiverApi;
import com.github.stevecommunity.ocpi.v221.web.api.LocationSenderApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class LocationClient extends AbstractClient implements LocationReceiverApi, LocationSenderApi {

    private static final String LOCATION_PATH   = "/{country_code}/{party_id}/{location_id}";
    private static final String EVSE_PATH       = "/{country_code}/{party_id}/{location_id}/{evse_uid}";
    private static final String CONNECTOR_PATH  = "/{country_code}/{party_id}/{location_id}/{evse_uid}/{connector_id}";

    private static final ParameterizedTypeReference<OcpiResponse<Location>> LOCATION_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<List<Location>>> LOCATIONS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<Evse>> EVSE_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<OcpiResponse<Connector>> CONNECTOR_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    public LocationClient(RestTemplate restTemplate, String locationsEndpointRoot, String authorizationToken) {
        super(locationsEndpointRoot, authorizationToken);
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public ResponseEntity<OcpiResponse<List<Location>>> getLocations(OcpiRequestHeaders headers,
                                                                     OcpiRequestParameters params) {
        return restTemplate.exchange(
            uri(params),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            LOCATIONS_RESPONSE
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Location>> getLocation(OcpiRequestHeaders headers, String locationId) {
        return restTemplate.exchange(
            endpoint("/{location_id}"),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            LOCATION_RESPONSE,
            locationId
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Location>> getLocation(OcpiRequestHeaders headers,
                                                              String countryCode,
                                                              String partyId,
                                                              String locationId) {
        return restTemplate.exchange(
            endpoint(LOCATION_PATH),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            LOCATION_RESPONSE,
            countryCode,
            partyId,
            locationId
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> putLocation(OcpiRequestHeaders headers,
                                                        String countryCode,
                                                        String partyId,
                                                        String locationId,
                                                        Location location) {
        return restTemplate.exchange(
            endpoint(LOCATION_PATH),
            HttpMethod.PUT,
            new HttpEntity<>(location, httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId,
            locationId
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> patchLocation(OcpiRequestHeaders headers,
                                                          String countryCode,
                                                          String partyId,
                                                          String locationId,
                                                          LocationPatch location) {
        return restTemplate.exchange(
            endpoint(LOCATION_PATH),
            HttpMethod.PATCH,
            new HttpEntity<>(location, httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId,
            locationId
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Evse>> getEvse(OcpiRequestHeaders headers, String locationId, String evseUid) {
        return restTemplate.exchange(
            endpoint("/{location_id}/{evse_uid}"),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            EVSE_RESPONSE,
            locationId,
            evseUid
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Evse>> getEvse(OcpiRequestHeaders headers,
                                                      String countryCode,
                                                      String partyId,
                                                      String locationId,
                                                      String evseUid) {
        return restTemplate.exchange(
            endpoint(EVSE_PATH),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            EVSE_RESPONSE,
            countryCode,
            partyId,
            locationId,
            evseUid
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> putEvse(OcpiRequestHeaders headers,
                                                    String countryCode,
                                                    String partyId,
                                                    String locationId,
                                                    String evseUid,
                                                    Evse evse) {
        return restTemplate.exchange(
            endpoint(EVSE_PATH),
            HttpMethod.PUT,
            new HttpEntity<>(evse, httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId,
            locationId,
            evseUid
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> patchEvse(OcpiRequestHeaders headers,
                                                      String countryCode,
                                                      String partyId,
                                                      String locationId,
                                                      String evseUid,
                                                      EvsePatch evse) {
        return restTemplate.exchange(
            endpoint(EVSE_PATH),
            HttpMethod.PATCH,
            new HttpEntity<>(evse, httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId,
            locationId,
            evseUid
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Connector>> getConnector(OcpiRequestHeaders headers,
                                                                String locationId,
                                                                String evseUid,
                                                                String connectorId) {
        return restTemplate.exchange(
            endpoint("/{location_id}/{evse_uid}/{connector_id}"),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            CONNECTOR_RESPONSE,
            locationId,
            evseUid,
            connectorId
        );
    }

    @Override
    public ResponseEntity<OcpiResponse<Connector>> getConnector(OcpiRequestHeaders headers,
                                                                String countryCode,
                                                                String partyId,
                                                                String locationId,
                                                                String evseUid,
                                                                String connectorId) {
        return restTemplate.exchange(
            endpoint(CONNECTOR_PATH),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders(headers)),
            CONNECTOR_RESPONSE,
            countryCode,
            partyId,
            locationId,
            evseUid,
            connectorId
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> putConnector(OcpiRequestHeaders headers,
                                                         String countryCode,
                                                         String partyId,
                                                         String locationId,
                                                         String evseUid,
                                                         String connectorId,
                                                         Connector connector) {
        return restTemplate.exchange(
            endpoint(CONNECTOR_PATH),
            HttpMethod.PUT,
            new HttpEntity<>(connector, httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId,
            locationId,
            evseUid,
            connectorId
        );
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> patchConnector(OcpiRequestHeaders headers,
                                                           String countryCode,
                                                           String partyId,
                                                           String locationId,
                                                           String evseUid,
                                                           String connectorId,
                                                           ConnectorPatch connector) {
        return restTemplate.exchange(
            endpoint(CONNECTOR_PATH),
            HttpMethod.PATCH,
            new HttpEntity<>(connector, httpHeaders(headers)),
            OcpiResponseVoid.class,
            countryCode,
            partyId,
            locationId,
            evseUid,
            connectorId
        );
    }
}
