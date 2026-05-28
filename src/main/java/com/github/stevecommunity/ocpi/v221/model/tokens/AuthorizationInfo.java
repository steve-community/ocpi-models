package com.github.stevecommunity.ocpi.v221.model.tokens;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.AllowedType;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.LocationReferences;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class AuthorizationInfo {
    @NotNull AllowedType allowed;
    @NotNull Token token;
    LocationReferences location;
    @Size(max = 36) String authorization_reference;
    DisplayText info;
}
