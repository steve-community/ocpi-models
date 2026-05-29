package com.github.stevecommunity.ocpi.v221.model.chargingprofiles;

import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types.ActiveChargingProfile;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types.ChargingProfileResultType;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class ActiveChargingProfileResult {
    @NotNull ChargingProfileResultType result;
    ActiveChargingProfile profile;
}
