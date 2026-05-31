package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.AbstractLocationNullChecks;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface LocationNullChecks extends AbstractLocationNullChecks {
    @NotEmpty String getCountry_code();
    @NotEmpty String getParty_id();
    @NotNull Boolean getPublish();
    @NotEmpty String getTime_zone();
}
