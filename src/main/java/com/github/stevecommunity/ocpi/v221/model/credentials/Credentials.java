package com.github.stevecommunity.ocpi.v221.model.credentials;

import com.github.stevecommunity.ocpi.v221.model.credentials.types.CredentialsRole;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Accessors(chain = true)
@Data
public class Credentials {
    @NotEmpty @Size(max = 64) String token;
    @NotEmpty @Size(max = 255) String url;
    @NotEmpty @Valid List<CredentialsRole> roles;
}
