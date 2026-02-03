package com.github.stevecommunity.ocpi.v221.model.tokens;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.CdrToken;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.ProfileType;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.EnergyContract;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.WhitelistType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Token extends CdrToken {
    @Size(max = 64) String visual_number;
    @NotNull @Size(max = 64) String issuer;
    @Size(max = 36) String group_id;
    @NotNull Boolean valid;
    @NotNull WhitelistType whitelist;
    @Size(max = 2) String language;
    ProfileType default_profile_type;
    EnergyContract energy_contract;
    @NotNull Instant last_updated;
}
