package com.github.stevecommunity.ocpi.v221.model;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.locations.types.GeoLocation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public abstract class AbstractLocation {
    @NotNull @Size(max = 36) String id;
    @Size(max = 255) String name;
    @NotNull @Size(max = 45) String address;
    @NotNull @Size(max = 45) String city;
    @NotNull @Size(max = 10) String postal_code;
    @Size(max = 20) String state;
    @NotNull @Size(max = 3) String country;
    @NotNull GeoLocation coordinates;
}
