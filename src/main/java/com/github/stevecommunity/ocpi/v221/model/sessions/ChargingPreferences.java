package com.github.stevecommunity.ocpi.v221.model.sessions;

import com.github.stevecommunity.ocpi.v221.model.sessions.types.ProfileType;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Accessors(chain = true)
@Data
public class ChargingPreferences {
    @NotNull ProfileType profile_type;
    Instant departure_time;
    Double energy_need;
    Boolean discharge_allowed;
}
