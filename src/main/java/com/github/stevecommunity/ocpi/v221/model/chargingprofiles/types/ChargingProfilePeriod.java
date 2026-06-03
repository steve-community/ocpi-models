package com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;

@Accessors(chain = true)
@Data
public class ChargingProfilePeriod {
    @NotNull Integer start_period;
    @NotNull Double limit;
}
