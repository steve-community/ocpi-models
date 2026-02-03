package com.github.stevecommunity.ocpi.v221.model.credentials;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.credentials.types.CredentialsRole;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Data
public class Credentials {
    @NotNull @Size(max = 64) String token;
    @NotNull @Size(max = 255) String url;
    @NotEmpty List<CredentialsRole> roles;
}
