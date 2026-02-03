package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;

import jakarta.validation.constraints.Size;

@Data
public class PublishTokenType {
    @Size(max = 36) String uid;
    TokenType type;
    @Size(max = 64) String visual_number;
    @Size(max = 64) String issuer;
    @Size(max = 36) String group_id;
}
