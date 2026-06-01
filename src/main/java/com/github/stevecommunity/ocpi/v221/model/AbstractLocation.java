package com.github.stevecommunity.ocpi.v221.model;

import com.github.stevecommunity.ocpi.v221.model.locations.types.GeoLocation;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@Data
public abstract class AbstractLocation {
    @Size(max = 36) String id;
    @Size(max = 255) String name;
    @Size(max = 45) String address;
    @Size(max = 45) String city;
    @Size(max = 10) String postal_code;
    @Size(max = 20) String state;
    @Size(max = 3) String country;
    @Valid GeoLocation coordinates;
}
