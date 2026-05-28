package com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
public class ChargingProfile {
    Instant start_date_time;
    Integer duration;
    @NotNull ChargingRateUnit charging_rate_unit;
    Double min_charging_rate;
    List<ChargingProfilePeriod> charging_profile_period;
}
