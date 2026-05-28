package com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class ActiveChargingProfile {
    @NotNull Instant start_date_time;
    @NotNull ChargingProfile charging_profile;
}
