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

    InterfaceRole getRole();
    ModuleID getId();
    String getPath();

    interface Receiver extends OcpiApi {

        @Override
        default InterfaceRole getRole() {
            return InterfaceRole.RECEIVER;
        }

        interface Cdrs             extends Receiver { @Override default ModuleID getId() { return cdrs;                   }
                                                      @Override default String getPath() { return CDRS_PATH;              } }
        interface ChargingProfiles extends Receiver { @Override default ModuleID getId() { return chargingprofiles;       }
                                                      @Override default String getPath() { return CHARGING_PROFILES_PATH; } }
        interface Commands         extends Receiver { @Override default ModuleID getId() { return commands;               }
                                                      @Override default String getPath() { return COMMANDS_PATH;          } }
        interface HubClientInfo    extends Receiver { @Override default ModuleID getId() { return hubclientinfo;          }
                                                      @Override default String getPath() { return HUB_CLIENT_INFO_PATH;   } }
        interface Locations        extends Receiver { @Override default ModuleID getId() { return locations;              }
                                                      @Override default String getPath() { return LOCATIONS_PATH;         } }
        interface Sessions         extends Receiver { @Override default ModuleID getId() { return sessions;               }
                                                      @Override default String getPath() { return SESSIONS_PATH;          } }
        interface Tariffs          extends Receiver { @Override default ModuleID getId() { return tariffs;                }
                                                      @Override default String getPath() { return TARIFFS_PATH;           } }
        interface Tokens           extends Receiver { @Override default ModuleID getId() { return tokens;                 }
                                                      @Override default String getPath() { return TOKENS_PATH;            } }
    }

    interface Sender extends OcpiApi {

        @Override
        default InterfaceRole getRole() {
            return InterfaceRole.SENDER;
        }

        interface Cdrs             extends Sender { @Override default ModuleID getId() { return cdrs;                   }
                                                    @Override default String getPath() { return CDRS_PATH;              } }
        interface ChargingProfiles extends Sender { @Override default ModuleID getId() { return chargingprofiles;       }
                                                    @Override default String getPath() { return CHARGING_PROFILES_PATH; } }
        interface Commands         extends Sender { @Override default ModuleID getId() { return commands;               }
                                                    @Override default String getPath() { return COMMANDS_PATH;          } }
        interface HubClientInfo    extends Sender { @Override default ModuleID getId() { return hubclientinfo;          }
                                                    @Override default String getPath() { return HUB_CLIENT_INFO_PATH;   } }
        interface Locations        extends Sender { @Override default ModuleID getId() { return locations;              }
                                                    @Override default String getPath() { return LOCATIONS_PATH;         } }
        interface Sessions         extends Sender { @Override default ModuleID getId() { return sessions;               }
                                                    @Override default String getPath() { return SESSIONS_PATH;          } }
        interface Tariffs          extends Sender { @Override default ModuleID getId() { return tariffs;                }
                                                    @Override default String getPath() { return TARIFFS_PATH;           } }
        interface Tokens           extends Sender { @Override default ModuleID getId() { return tokens;                 }
                                                    @Override default String getPath() { return TOKENS_PATH;            } }
    }
}
