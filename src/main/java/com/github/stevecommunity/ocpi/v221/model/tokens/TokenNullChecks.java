package com.github.stevecommunity.ocpi.v221.model.tokens;

import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.WhitelistType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface TokenNullChecks {
    @NotEmpty String getCountry_code();
    @NotEmpty String getParty_id();
    @NotEmpty String getUid();
    @NotNull TokenType getType();
    @NotEmpty String getContract_id();
    @NotEmpty String getIssuer();
    @NotNull Boolean getValid();
    @NotNull WhitelistType getWhitelist();
}
