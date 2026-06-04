package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.cdrs.Cdr;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ChargingProfileResponse;
import com.github.stevecommunity.ocpi.v221.model.commands.CommandResponse;
import com.github.stevecommunity.ocpi.v221.model.credentials.Credentials;
import com.github.stevecommunity.ocpi.v221.model.hubclientinfo.ClientInfo;
import com.github.stevecommunity.ocpi.v221.model.locations.Connector;
import com.github.stevecommunity.ocpi.v221.model.locations.Evse;
import com.github.stevecommunity.ocpi.v221.model.locations.Location;
import com.github.stevecommunity.ocpi.v221.model.sessions.Session;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.ChargingPreferencesResponse;
import com.github.stevecommunity.ocpi.v221.model.tariffs.Tariff;
import com.github.stevecommunity.ocpi.v221.model.tokens.AuthorizationInfo;
import com.github.stevecommunity.ocpi.v221.model.tokens.Token;
import com.github.stevecommunity.ocpi.v221.model.versions.Version;
import com.github.stevecommunity.ocpi.v221.model.versions.VersionDetails;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class Constants {

    static final ParameterizedTypeReference<OcpiResponse<Cdr>> CDR_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<List<Cdr>>> CDRS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<ChargingProfileResponse>> CHARGING_PROFILE_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<CommandResponse>> COMMAND_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<Credentials>> CREDENTIALS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<ClientInfo>> CLIENT_INFO_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<List<ClientInfo>>> CLIENT_INFOS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<Location>> LOCATION_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<List<Location>>> LOCATIONS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<Evse>> EVSE_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<Connector>> CONNECTOR_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<Session>> SESSION_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<List<Session>>> SESSIONS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<ChargingPreferencesResponse>> CHARGING_PREFERENCES_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<Tariff>> TARIFF_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<List<Tariff>>> TARIFFS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<Token>> TOKEN_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<List<Token>>> TOKENS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<AuthorizationInfo>> AUTHORIZATION_INFO_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<List<Version>>> VERSIONS_RESPONSE = new ParameterizedTypeReference<>() {
    };
    static final ParameterizedTypeReference<OcpiResponse<VersionDetails>> VERSION_DETAILS_RESPONSE = new ParameterizedTypeReference<>() {
    };
}
