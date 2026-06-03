package com.github.stevecommunity.ocpi.v221.model.chargingprofiles;

import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types.ChargingProfile;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Accessors(chain = true)
@Data
public class SetChargingProfile {
    @NotNull @Valid ChargingProfile charging_profile;
    @NotEmpty @Size(max = 255) String response_url;
}
