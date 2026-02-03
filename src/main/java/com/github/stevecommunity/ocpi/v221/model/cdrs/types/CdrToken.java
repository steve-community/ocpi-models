package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class CdrToken {
    @NotNull @Size(max = 2) String country_code;
    @NotNull @Size(max = 3) String party_id;
    @NotNull @Size(max = 36) String uid;
    @NotNull TokenType type;
    @NotNull @Size(max = 36) String contract_id;
}
