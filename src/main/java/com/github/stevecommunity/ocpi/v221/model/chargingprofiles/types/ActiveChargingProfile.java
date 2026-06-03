package com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Accessors(chain = true)
@Data
public class ActiveChargingProfile {
    @NotNull Instant start_date_time;
    @NotNull @Valid ChargingProfile charging_profile;
}
