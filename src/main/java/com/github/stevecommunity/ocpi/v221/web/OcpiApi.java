package com.github.stevecommunity.ocpi.v221.web;

import com.github.stevecommunity.ocpi.v221.model.versions.types.InterfaceRole;
import com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID;
import com.github.stevecommunity.ocpi.v221.model.versions.types.VersionNumber;

import static com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID.cdrs;
import static com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID.chargingprofiles;
import static com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID.commands;
import static com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID.credentials;
import static com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID.hubclientinfo;
import static com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID.locations;
import static com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID.sessions;
import static com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID.tariffs;
import static com.github.stevecommunity.ocpi.v221.model.versions.types.ModuleID.tokens;

public interface OcpiApi {

    VersionNumber THIS_VERSION = VersionNumber.V_2_2_1;

    String VERSIONS_PATH          = "/ocpi/versions";
    String CDRS_PATH              = "/ocpi/2.2.1/cdrs";
    String CHARGING_PROFILES_PATH = "/ocpi/2.2.1/chargingprofiles";
    String COMMANDS_PATH          = "/ocpi/2.2.1/commands";
    String CREDENTIALS_PATH       = "/ocpi/2.2.1/credentials";
    String HUB_CLIENT_INFO_PATH   = "/ocpi/2.2.1/hubclientinfo";
    String LOCATIONS_PATH         = "/ocpi/2.2.1/locations";
    String SESSIONS_PATH          = "/ocpi/2.2.1/sessions";
    String TARIFFS_PATH           = "/ocpi/2.2.1/tariffs";
    String TOKENS_PATH            = "/ocpi/2.2.1/tokens";

    String HEADER_X_REQUEST_ID        = "X-Request-ID";
    String HEADER_X_CORRELATION_ID    = "X-Correlation-ID";
    String HEADER_OCPI_FROM_COUNTRY   = "OCPI-from-country-code";
    String HEADER_OCPI_FROM_PARTY_ID  = "OCPI-from-party-id";
    String HEADER_OCPI_TO_COUNTRY     = "OCPI-to-country-code";
    String HEADER_OCPI_TO_PARTY_ID    = "OCPI-to-party-id";

    String HEADER_RESPONSE_LINK        = "Link";
    String HEADER_RESPONSE_TOTAL_COUNT = "X-Total-Count";
    String HEADER_RESPONSE_LIMIT       = "X-Limit";

    InterfaceRole getRole();
    ModuleID getId();
    String getPath();

    interface Sender extends OcpiApi {
        @Override default InterfaceRole getRole() { return InterfaceRole.SENDER; }
    }

    interface Receiver extends OcpiApi {
        @Override default InterfaceRole getRole() { return InterfaceRole.RECEIVER; }
    }

    interface Credentialz extends OcpiApi {
        @Override default InterfaceRole getRole() { return InterfaceRole.SENDER; }
        @Override default ModuleID getId() { return credentials; }
        @Override default String getPath() { return CREDENTIALS_PATH; }
    }

    interface Cdrs extends OcpiApi {
        @Override default ModuleID getId() { return cdrs; }
        @Override default String getPath() { return CDRS_PATH; }
        interface Sender extends Cdrs, OcpiApi.Sender { }
        interface Receiver extends Cdrs, OcpiApi.Receiver { }
    }

    interface ChargingProfiles extends OcpiApi {
        @Override default ModuleID getId() { return chargingprofiles; }
        @Override default String getPath() { return CHARGING_PROFILES_PATH; }
        interface Sender extends ChargingProfiles, OcpiApi.Sender { }
        interface Receiver extends ChargingProfiles, OcpiApi.Receiver { }
    }

    interface Commands extends OcpiApi {
        @Override default ModuleID getId() { return commands; }
        @Override default String getPath() { return COMMANDS_PATH; }
        interface Sender extends Commands, OcpiApi.Sender { }
        interface Receiver extends Commands, OcpiApi.Receiver { }
    }

    interface HubClientInfo extends OcpiApi {
        @Override default ModuleID getId() { return hubclientinfo; }
        @Override default String getPath() { return HUB_CLIENT_INFO_PATH; }
        interface Sender extends HubClientInfo, OcpiApi.Sender { }
        interface Receiver extends HubClientInfo, OcpiApi.Receiver { }
    }

    interface Locations extends OcpiApi {
        @Override default ModuleID getId() { return locations; }
        @Override default String getPath() { return LOCATIONS_PATH; }
        interface Sender extends Locations, OcpiApi.Sender { }
        interface Receiver extends Locations, OcpiApi.Receiver { }
    }

    interface Sessions extends OcpiApi {
        @Override default ModuleID getId() { return sessions; }
        @Override default String getPath() { return SESSIONS_PATH; }
        interface Sender extends Sessions, OcpiApi.Sender { }
        interface Receiver extends Sessions, OcpiApi.Receiver { }
    }

    interface Tariffs extends OcpiApi {
        @Override default ModuleID getId() { return tariffs; }
        @Override default String getPath() { return TARIFFS_PATH; }
        interface Sender extends Tariffs, OcpiApi.Sender { }
        interface Receiver extends Tariffs, OcpiApi.Receiver { }
    }

    interface Tokens extends OcpiApi {
        @Override default ModuleID getId() { return tokens; }
        @Override default String getPath() { return TOKENS_PATH; }
        interface Sender extends Tokens, OcpiApi.Sender { }
        interface Receiver extends Tokens, OcpiApi.Receiver { }
    }
}
