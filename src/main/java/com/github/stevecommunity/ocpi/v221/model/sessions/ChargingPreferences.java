package com.github.stevecommunity.ocpi.v221.model.sessions;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.ProfileType;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class ChargingPreferences {
    @NotNull ProfileType profile_type;
    Instant departure_time;
    Double energy_need;
    Boolean discharge_allowed;
}
