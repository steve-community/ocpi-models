package com.github.stevecommunity.ocpi.v221.model;

import com.github.stevecommunity.ocpi.v221.model.locations.types.GeoLocation;

import jakarta.validation.constraints.NotNull;

public interface AbstractLocationNullChecks {
    @NotNull String getId();
    @NotNull String getAddress();
    @NotNull String getCity();
    @NotNull String getCountry();
    @NotNull GeoLocation getCoordinates();
}
