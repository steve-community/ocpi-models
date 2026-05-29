package com.github.stevecommunity.ocpi.v221.model.tokens;

import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.WhitelistType;

import jakarta.validation.constraints.NotNull;

public interface TokenNullChecks {
    @NotNull String getCountry_code();
    @NotNull String getParty_id();
    @NotNull String getUid();
    @NotNull TokenType getType();
    @NotNull String getContract_id();
    @NotNull String getIssuer();
    @NotNull Boolean getValid();
    @NotNull WhitelistType getWhitelist();
}
