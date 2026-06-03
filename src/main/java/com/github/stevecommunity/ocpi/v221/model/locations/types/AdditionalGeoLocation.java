package com.github.stevecommunity.ocpi.v221.model.locations.types;

import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;

@Accessors(chain = true)
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdditionalGeoLocation extends GeoLocation {
    /**
     * Name of the point.
     */
    @Valid DisplayText name;
}
