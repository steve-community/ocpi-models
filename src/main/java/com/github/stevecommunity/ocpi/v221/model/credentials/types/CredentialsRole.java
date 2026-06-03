package com.github.stevecommunity.ocpi.v221.model.credentials.types;

import com.github.stevecommunity.ocpi.v221.model.Role;
import com.github.stevecommunity.ocpi.v221.model.locations.types.BusinessDetails;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Accessors(chain = true)
@Data
public class CredentialsRole {
    @NotNull Role role;
    @NotNull @Valid BusinessDetails business_details;
    @NotEmpty @Size(max = 3) String party_id;
    @NotEmpty @Size(max = 2) String country_code;
}
