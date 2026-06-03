package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Accessors(chain = true)
@Data
public class CdrToken {
    @NotEmpty @Size(max = 2) String country_code;
    @NotEmpty @Size(max = 3) String party_id;
    @NotEmpty @Size(max = 36) String uid;
    @NotNull TokenType type;
    @NotEmpty @Size(max = 36) String contract_id;
}
