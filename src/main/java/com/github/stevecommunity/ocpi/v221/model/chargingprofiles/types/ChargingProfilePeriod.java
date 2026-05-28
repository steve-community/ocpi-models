package com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class ChargingProfilePeriod {
    @NotNull Integer start_period;
    @NotNull Double limit;
}
