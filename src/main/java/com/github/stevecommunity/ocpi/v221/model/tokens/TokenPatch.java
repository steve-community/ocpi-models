package com.github.stevecommunity.ocpi.v221.model.tokens;

import com.github.stevecommunity.ocpi.v221.model.sessions.types.ProfileType;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.EnergyContract;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.WhitelistType;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Data
public class TokenPatch {
    @Size(max = 2) String country_code;
    @Size(max = 3) String party_id;
    @Size(max = 36) String uid;
    TokenType type;
    @Size(max = 36) String contract_id;
    @Size(max = 64) String visual_number;
    @Size(max = 64) String issuer;
    @Size(max = 36) String group_id;
    Boolean valid;
    WhitelistType whitelist;
    @Size(max = 2) String language;
    ProfileType default_profile_type;
    EnergyContract energy_contract;
    @NotNull Instant last_updated;
}
