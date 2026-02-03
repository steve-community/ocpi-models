package com.github.stevecommunity.ocpi.v221.model.credentials.types;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.Role;
import com.github.stevecommunity.ocpi.v221.model.locations.types.BusinessDetails;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class CredentialsRole {
    @NotNull Role role;
    @NotNull BusinessDetails business_details;
    @NotNull @Size(max = 3) String party_id;
    @NotNull @Size(max = 2) String country_code;
}
