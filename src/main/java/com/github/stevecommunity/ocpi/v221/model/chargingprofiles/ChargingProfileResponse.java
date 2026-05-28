package com.github.stevecommunity.ocpi.v221.model.chargingprofiles;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types.ChargingProfileResponseType;

import jakarta.validation.constraints.NotNull;

@Data
public class ChargingProfileResponse {
    @NotNull ChargingProfileResponseType result;
    @NotNull Integer timeout;
}
