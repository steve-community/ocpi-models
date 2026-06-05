package com.github.stevecommunity.ocpi.v221.model.tokens;

import com.github.stevecommunity.ocpi.v221.model.locations.types.PublishTokenType;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.ProfileType;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.EnergyContract;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.WhitelistType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Accessors(chain = true)
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TokenPatch extends PublishTokenType {
    @Size(max = 2) String country_code;
    @Size(max = 3) String party_id;
    @Size(max = 36) String contract_id;
    Boolean valid;
    WhitelistType whitelist;
    @Size(max = 2) String language;
    ProfileType default_profile_type;
    @Valid EnergyContract energy_contract;
    @NotNull Instant last_updated;
}
