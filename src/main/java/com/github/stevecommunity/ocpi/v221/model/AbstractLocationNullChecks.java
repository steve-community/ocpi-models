package com.github.stevecommunity.ocpi.v221.model;

import com.github.stevecommunity.ocpi.v221.model.locations.types.GeoLocation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface AbstractLocationNullChecks {
    @NotEmpty String getId();
    @NotEmpty String getAddress();
    @NotEmpty String getCity();
    @NotEmpty String getCountry();
    @NotNull GeoLocation getCoordinates();
}
