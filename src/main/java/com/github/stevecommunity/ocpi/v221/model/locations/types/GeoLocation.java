package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.github.stevecommunity.ocpi.v221.util.ValidationPatterns.LATITUDE;
import static com.github.stevecommunity.ocpi.v221.util.ValidationPatterns.LONGITUDE;

@Data
public class GeoLocation {
    /**
     * Latitude of the point in decimal degree. Example: 50.770774. Decimal
     * separator: "." Regex: -?[0-9]{1,2}\.[0-9]{5,7}
     */
    @NotNull @Size(max = 10) @Pattern(regexp = LATITUDE) String latitude;

    /**
     * Longitude of the point in decimal degree. Example: -126.104965. Decimal
     * separator: "." Regex: -?[0-9]{1,3}\.[0-9]{5,7}
     */
    @NotNull @Size(max = 11) @Pattern(regexp = LONGITUDE) String longitude;
}
