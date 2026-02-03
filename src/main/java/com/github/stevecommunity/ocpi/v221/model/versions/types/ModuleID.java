package com.github.stevecommunity.ocpi.v221.model.versions.types;

public enum ModuleID {
    cdrs,
    chargingprofiles,
    commands,
    credentials, // Required for all implementations. The role field has no function for this module.
    hubclientinfo,
    locations,
    sessions,
    tariffs,
    tokens,
    versions
}
