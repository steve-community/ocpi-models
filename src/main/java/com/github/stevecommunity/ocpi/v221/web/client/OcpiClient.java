package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.cdrs.Cdr;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ActiveChargingProfileResult;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ChargingProfileResponse;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ChargingProfileResult;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ClearProfileResult;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.SetChargingProfile;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types.ActiveChargingProfile;
import com.github.stevecommunity.ocpi.v221.model.commands.CancelReservation;
import com.github.stevecommunity.ocpi.v221.model.commands.CommandResponse;
import com.github.stevecommunity.ocpi.v221.model.commands.CommandResult;
import com.github.stevecommunity.ocpi.v221.model.commands.ReserveNow;
import com.github.stevecommunity.ocpi.v221.model.commands.StartSession;
import com.github.stevecommunity.ocpi.v221.model.commands.StopSession;
import com.github.stevecommunity.ocpi.v221.model.commands.UnlockConnector;
import com.github.stevecommunity.ocpi.v221.model.credentials.Credentials;
import com.github.stevecommunity.ocpi.v221.model.hubclientinfo.ClientInfo;
import com.github.stevecommunity.ocpi.v221.model.locations.Connector;
import com.github.stevecommunity.ocpi.v221.model.locations.ConnectorPatch;
import com.github.stevecommunity.ocpi.v221.model.locations.Evse;
import com.github.stevecommunity.ocpi.v221.model.locations.EvsePatch;
import com.github.stevecommunity.ocpi.v221.model.locations.Location;
import com.github.stevecommunity.ocpi.v221.model.locations.LocationPatch;
import com.github.stevecommunity.ocpi.v221.model.sessions.ChargingPreferences;
import com.github.stevecommunity.ocpi.v221.model.sessions.Session;
import com.github.stevecommunity.ocpi.v221.model.sessions.SessionPatch;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.ChargingPreferencesResponse;
import com.github.stevecommunity.ocpi.v221.model.tariffs.Tariff;
import com.github.stevecommunity.ocpi.v221.model.tokens.AuthorizationInfo;
import com.github.stevecommunity.ocpi.v221.model.tokens.Token;
import com.github.stevecommunity.ocpi.v221.model.tokens.TokenPatch;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.LocationReferences;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import com.github.stevecommunity.ocpi.v221.model.versions.Version;
import com.github.stevecommunity.ocpi.v221.model.versions.VersionDetails;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_X_CORRELATION_ID;
import static com.github.stevecommunity.ocpi.v221.web.OcpiApi.HEADER_X_REQUEST_ID;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.AUTHORIZATION_INFO_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.CDRS_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.CDR_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.CHARGING_PREFERENCES_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.CHARGING_PROFILE_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.CLIENT_INFOS_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.CLIENT_INFO_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.COMMAND_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.CONNECTOR_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.CREDENTIALS_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.EVSE_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.LOCATIONS_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.LOCATION_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.SESSIONS_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.SESSION_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.TARIFFS_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.TARIFF_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.TOKENS_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.TOKEN_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.VERSIONS_RESPONSE;
import static com.github.stevecommunity.ocpi.v221.web.client.Constants.VERSION_DETAILS_RESPONSE;

public class OcpiClient {

    private final RestTemplate restTemplate;
    private final HttpHeaders staticHeaders;

    OcpiClient(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        // Snapshot the builder headers so later builder mutations cannot affect this client.
        this.staticHeaders = HttpHeaders.copyOf(headers);
    }

    // -------------------------------------------------------------------------
    // Versions
    // -------------------------------------------------------------------------

    public List<Version> getVersions(String url) {
        return get(url, VERSIONS_RESPONSE);
    }

    public VersionDetails getVersionDetails(String url) {
        return get(url, VERSION_DETAILS_RESPONSE);
    }

    // -------------------------------------------------------------------------
    // Credentials
    // -------------------------------------------------------------------------

    public Credentials getCredentials(String url) {
        return get(url, CREDENTIALS_RESPONSE);
    }

    public Credentials postCredentials(String url, Credentials credentials) {
        return post(url, credentials, CREDENTIALS_RESPONSE);
    }

    public Credentials putCredentials(String url, Credentials credentials) {
        return put(url, credentials, CREDENTIALS_RESPONSE);
    }

    public void deleteCredentials(String url) {
        delete(url);
    }

    // -------------------------------------------------------------------------
    // CDRs
    // -------------------------------------------------------------------------

    public List<Cdr> getCdrs(String url, OcpiRequestParameters params) {
        String completeUrl = addPagination(url, params);
        return get(completeUrl, CDRS_RESPONSE);
    }

    public Cdr getCdr(String url, String cdrId) {
        String completeUrl = buildWithPaths(url, cdrId);
        return get(completeUrl, CDR_RESPONSE);
    }

    public String postCdr(String url, Cdr cdr) {
        var response = post(url, cdr);
        return response.getHeaders().getFirst(HttpHeaders.LOCATION);
    }

    // -------------------------------------------------------------------------
    // ChargingProfiles
    // -------------------------------------------------------------------------

    public ChargingProfileResponse getActiveChargingProfile(String url, String sessionId, Integer duration, String responseUrl) {
        String completeUrl = builderWithPaths(url, sessionId)
            .queryParamIfPresent("duration", Optional.ofNullable(duration))
            .queryParamIfPresent("response_url", Optional.ofNullable(responseUrl))
            .encode().toUriString();

        return get(completeUrl, CHARGING_PROFILE_RESPONSE);
    }

    public ChargingProfileResponse putChargingProfile(String url, String sessionId, SetChargingProfile chargingProfile) {
        String completeUrl = buildWithPaths(url, sessionId);
        return put(completeUrl, chargingProfile, CHARGING_PROFILE_RESPONSE);
    }

    public ChargingProfileResponse deleteChargingProfile(String url, String sessionId, String responseUrl) {
        String completeUrl = builderWithPaths(url, sessionId)
            .queryParamIfPresent("response_url", Optional.ofNullable(responseUrl))
            .encode().toUriString();

        return delete(completeUrl, CHARGING_PROFILE_RESPONSE);
    }

    public void putActiveChargingProfile(String completeUrl, ActiveChargingProfile activeChargingProfile) {
        put(completeUrl, activeChargingProfile);
    }

    public void sendResult(String completeUrl, ActiveChargingProfileResult result) {
        post(completeUrl, result);
    }

    public void sendResult(String completeUrl, ChargingProfileResult result) {
        post(completeUrl, result);
    }

    public void sendResult(String completeUrl, ClearProfileResult result) {
        post(completeUrl, result);
    }

    // -------------------------------------------------------------------------
    // Commands
    // -------------------------------------------------------------------------

    public CommandResponse reserveNow(String url, ReserveNow command) {
        String completeUrl = buildWithPaths(url, "RESERVE_NOW");
        return post(completeUrl, command, COMMAND_RESPONSE);
    }

    public CommandResponse cancelReservation(String url, CancelReservation command) {
        String completeUrl = buildWithPaths(url, "CANCEL_RESERVATION");
        return post(completeUrl, command, COMMAND_RESPONSE);
    }

    public CommandResponse startSession(String url, StartSession command) {
        String completeUrl = buildWithPaths(url, "START_SESSION");
        return post(completeUrl, command, COMMAND_RESPONSE);
    }

    public CommandResponse stopSession(String url, StopSession command) {
        String completeUrl = buildWithPaths(url, "STOP_SESSION");
        return post(completeUrl, command, COMMAND_RESPONSE);
    }

    public CommandResponse unlockConnector(String url, UnlockConnector command) {
        String completeUrl = buildWithPaths(url, "UNLOCK_CONNECTOR");
        return post(completeUrl, command, COMMAND_RESPONSE);
    }

    public void sendResult(String completeUrl, CommandResult result) {
        post(completeUrl, result);
    }

    // -------------------------------------------------------------------------
    // HubClientInfo
    // -------------------------------------------------------------------------

    public List<ClientInfo> getClientInfos(String url, OcpiRequestParameters params) {
        String completeUrl = addPagination(url, params);
        return get(completeUrl, CLIENT_INFOS_RESPONSE);
    }

    public ClientInfo getClientInfo(String url, String countryCode, String partyId) {
        String completeUrl = buildWithPaths(url, countryCode, partyId);
        return get(completeUrl, CLIENT_INFO_RESPONSE);
    }

    public void putClientInfo(String url, String countryCode, String partyId, ClientInfo clientInfo) {
        String completeUrl = buildWithPaths(url, countryCode, partyId);
        put(completeUrl, clientInfo);
    }

    // -------------------------------------------------------------------------
    // Locations
    // -------------------------------------------------------------------------

    public List<Location> getLocations(String url, OcpiRequestParameters params) {
        String completeUrl = addPagination(url, params);
        return get(completeUrl, LOCATIONS_RESPONSE);
    }

    public Location getLocation(String url,
                                String locationId) {
        String completeUrl = buildWithPaths(url, locationId);
        return get(completeUrl, LOCATION_RESPONSE);
    }

    public Location getLocation(String url,
                                String countryCode, String partyId, String locationId) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, locationId);
        return get(completeUrl, LOCATION_RESPONSE);
    }

    public void putLocation(String url,
                            String countryCode, String partyId, String locationId,
                            Location location) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, locationId);
        put(completeUrl, location);
    }

    public void patchLocation(String url,
                              String countryCode, String partyId, String locationId,
                              LocationPatch location) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, locationId);
        patch(completeUrl, location);
    }

    public Evse getEvse(String url,
                        String locationId, String evseUid) {
        String completeUrl = buildWithPaths(url, locationId, evseUid);
        return get(completeUrl, EVSE_RESPONSE);
    }

    public Evse getEvse(String url,
                        String countryCode, String partyId, String locationId, String evseUid) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, locationId, evseUid);
        return get(completeUrl, EVSE_RESPONSE);
    }

    public void putEvse(String url,
                        String countryCode, String partyId, String locationId, String evseUid,
                        Evse evse) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, locationId, evseUid);
        put(completeUrl, evse);
    }

    public void patchEvse(String url,
                          String countryCode, String partyId, String locationId, String evseUid,
                          EvsePatch evse) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, locationId, evseUid);
        patch(completeUrl, evse);
    }

    public Connector getConnector(String url,
                                  String locationId, String evseUid, String connectorId) {
        String completeUrl = buildWithPaths(url, locationId, evseUid, connectorId);
        return get(completeUrl, CONNECTOR_RESPONSE);
    }

    public Connector getConnector(String url,
                                  String countryCode, String partyId, String locationId, String evseUid, String connectorId) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, locationId, evseUid, connectorId);
        return get(completeUrl, CONNECTOR_RESPONSE);
    }

    public void putConnector(String url,
                             String countryCode, String partyId, String locationId, String evseUid, String connectorId,
                             Connector connector) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, locationId, evseUid, connectorId);
        put(completeUrl, connector);
    }

    public void patchConnector(String url,
                               String countryCode, String partyId, String locationId, String evseUid, String connectorId,
                               ConnectorPatch connector) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, locationId, evseUid, connectorId);
        patch(completeUrl, connector);
    }

    // -------------------------------------------------------------------------
    // Sessions
    // -------------------------------------------------------------------------

    public List<Session> getSessions(String url, OcpiRequestParameters params) {
        String completeUrl = addPagination(url, params);
        return get(completeUrl, SESSIONS_RESPONSE);
    }

    public Session getSession(String url,
                              String countryCode, String partyId, String sessionId) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, sessionId);
        return get(completeUrl, SESSION_RESPONSE);
    }

    public void putSession(String url,
                           String countryCode, String partyId, String sessionId,
                           Session session) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, sessionId);
        put(completeUrl, session);
    }

    public void patchSession(String url,
                             String countryCode, String partyId, String sessionId,
                             SessionPatch session) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, sessionId);
        patch(completeUrl, session);
    }

    public ChargingPreferencesResponse putChargingPreferences(String url, String sessionId, ChargingPreferences chargingPreferences) {
        String completeUrl = buildWithPaths(url, sessionId, "charging_preferences");
        return put(completeUrl, chargingPreferences, CHARGING_PREFERENCES_RESPONSE);
    }

    // -------------------------------------------------------------------------
    // Tariffs
    // -------------------------------------------------------------------------

    public List<Tariff> getTariffs(String url, OcpiRequestParameters params) {
        String completeUrl = addPagination(url, params);
        return get(completeUrl, TARIFFS_RESPONSE);
    }

    public Tariff getTariff(String url,
                            String countryCode, String partyId, String tariffId) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, tariffId);
        return get(completeUrl, TARIFF_RESPONSE);
    }

    public void putTariff(String url,
                          String countryCode, String partyId, String tariffId,
                          Tariff tariff) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, tariffId);
        put(completeUrl, tariff);
    }

    public void deleteTariff(String url,
                             String countryCode, String partyId, String tariffId) {
        String completeUrl = buildWithPaths(url, countryCode, partyId, tariffId);
        delete(completeUrl);
    }

    // -------------------------------------------------------------------------
    // Tokens
    // -------------------------------------------------------------------------

    public List<Token> getTokens(String url, OcpiRequestParameters params) {
        String completeUrl = addPagination(url, params);
        return get(completeUrl, TOKENS_RESPONSE);
    }

    public Token getToken(String url, String countryCode, String partyId, String tokenUid, TokenType type) {
        String completeUrl = builderWithPaths(url, countryCode, partyId, tokenUid)
            .queryParamIfPresent("type", Optional.ofNullable(type))
            .encode().toUriString();

        return get(completeUrl, TOKEN_RESPONSE);
    }

    public void putToken(String url,
                         String countryCode, String partyId, String tokenUid, TokenType type,
                         Token token) {
        String completeUrl = builderWithPaths(url, countryCode, partyId, tokenUid)
            .queryParamIfPresent("type", Optional.ofNullable(type))
            .encode().toUriString();

        put(completeUrl, token);
    }

    public void patchToken(String url,
                           String countryCode, String partyId, String tokenUid, TokenType type,
                           TokenPatch token) {
        String completeUrl = builderWithPaths(url, countryCode, partyId, tokenUid)
            .queryParamIfPresent("type", Optional.ofNullable(type))
            .encode().toUriString();

        patch(completeUrl, token);
    }

    public AuthorizationInfo authorizeToken(String url,
                                            String tokenUid, TokenType type,
                                            LocationReferences locationReferences) {
        String completeUrl = builderWithPaths(url, tokenUid, "authorize")
            .queryParamIfPresent("type", Optional.ofNullable(type))
            .encode().toUriString();

        return post(completeUrl, locationReferences, AUTHORIZATION_INFO_RESPONSE);
    }

    // -------------------------------------------------------------------------
    // Generics
    // -------------------------------------------------------------------------

    public <INNER, OUTER extends OcpiResponse<INNER>> INNER get(String url,
                                                                ParameterizedTypeReference<OUTER> responseType) {
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders()),
            responseType
        ).getBody().getData();
    }

    public <INNER, OUTER extends OcpiResponse<INNER>> INNER post(String url,
                                                                 Object payload,
                                                                 ParameterizedTypeReference<OUTER> responseType) {
        return restTemplate.exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<>(payload, httpHeaders()),
            responseType
        ).getBody().getData();
    }

    public <INNER, OUTER extends OcpiResponse<INNER>> INNER put(String url,
                                                                Object payload,
                                                                ParameterizedTypeReference<OUTER> responseType) {
        return restTemplate.exchange(
            url,
            HttpMethod.PUT,
            new HttpEntity<>(payload, httpHeaders()),
            responseType
        ).getBody().getData();
    }

    public <INNER, OUTER extends OcpiResponse<INNER>> INNER delete(String url,
                                                                   ParameterizedTypeReference<OUTER> responseType) {
        return restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            new HttpEntity<>(httpHeaders()),
            responseType
        ).getBody().getData();
    }

    public ResponseEntity<OcpiResponseVoid> post(String url, Object payload) {
        return restTemplate.exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<>(payload, httpHeaders()),
            OcpiResponseVoid.class
        );
    }

    public ResponseEntity<OcpiResponseVoid> put(String url, Object payload) {
        return restTemplate.exchange(
            url,
            HttpMethod.PUT,
            new HttpEntity<>(payload, httpHeaders()),
            OcpiResponseVoid.class
        );
    }

    public ResponseEntity<OcpiResponseVoid> patch(String url, Object payload) {
        return restTemplate.exchange(
            url,
            HttpMethod.PATCH,
            new HttpEntity<>(payload, httpHeaders()),
            OcpiResponseVoid.class
        );
    }

    public ResponseEntity<OcpiResponseVoid> delete(String url) {
        return restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            new HttpEntity<>(httpHeaders()),
            OcpiResponseVoid.class
        );
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    private HttpHeaders httpHeaders() {
        // Copy the static template because request and default correlation IDs are per exchange.
        HttpHeaders headers = HttpHeaders.copyOf(staticHeaders);

        headers.set(HEADER_X_REQUEST_ID, UUID.randomUUID().toString());

        if (!headers.containsHeader(HEADER_X_CORRELATION_ID)) {
            headers.set(HEADER_X_CORRELATION_ID, UUID.randomUUID().toString());
        }

        return headers;
    }

    private static String addPagination(String url, OcpiRequestParameters params) {
        if (params == null) {
            return url;
        }

        return UriComponentsBuilder
            .fromUriString(url)
            .queryParamIfPresent("date_from", Optional.ofNullable(params.getDate_from()))
            .queryParamIfPresent("date_to", Optional.ofNullable(params.getDate_to()))
            .queryParamIfPresent("offset", Optional.ofNullable(params.getOffset()))
            .queryParamIfPresent("limit", Optional.ofNullable(params.getLimit()))
            .encode()
            .toUriString();
    }

    private static String buildWithPaths(String baseUrl, Object... paths) {
        return builderWithPaths(baseUrl, paths).encode().toUriString();
    }

    private static UriComponentsBuilder builderWithPaths(String baseUrl, Object... paths) {
        var builder = UriComponentsBuilder.fromUriString(baseUrl);
        for (Object path : paths) {
            if (path != null) {
                builder.pathSegment(path.toString());
            }
        }
        return builder;
    }
}
