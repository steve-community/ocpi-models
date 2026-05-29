package com.github.stevecommunity.ocpi.v221.model.locations.types;

import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdditionalGeoLocation extends GeoLocation {
    /**
     * Name of the point.
     */
    DisplayText name;
}
