package com.github.stevecommunity.ocpi.v221.model.chargingprofiles;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types.ChargingProfile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class SetChargingProfile {
    @NotNull ChargingProfile charging_profile;
    @NotNull @Size(max = 255) String response_url;
}
