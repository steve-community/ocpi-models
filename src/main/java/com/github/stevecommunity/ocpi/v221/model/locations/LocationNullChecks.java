package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.AbstractLocationNullChecks;

import jakarta.validation.constraints.NotNull;

public interface LocationNullChecks extends AbstractLocationNullChecks {
    @NotNull String getCountry_code();
    @NotNull String getParty_id();
    @NotNull Boolean getPublish();
    @NotNull String getTime_zone();
}
