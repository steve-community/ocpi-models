package com.github.stevecommunity.ocpi.v221.model.tokens;

import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.AllowedType;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.LocationReferences;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class AuthorizationInfo {
    @NotNull AllowedType allowed;
    @NotNull @Valid Token token;
    @Valid LocationReferences location;
    @Size(max = 36) String authorization_reference;
    @Valid DisplayText info;
}
