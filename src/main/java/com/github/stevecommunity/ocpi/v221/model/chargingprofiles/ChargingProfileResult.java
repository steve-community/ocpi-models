package com.github.stevecommunity.ocpi.v221.model.chargingprofiles;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types.ChargingProfileResultType;

import jakarta.validation.constraints.NotNull;

@Data
public class ChargingProfileResult {
    @NotNull ChargingProfileResultType resultType;
}
