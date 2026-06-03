package com.github.stevecommunity.ocpi.v221.model.hubclientinfo;

import com.github.stevecommunity.ocpi.v221.model.Role;
import com.github.stevecommunity.ocpi.v221.model.hubclientinfo.types.ConnectionStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Accessors(chain = true)
@Data
public class ClientInfo {
    @NotEmpty @Size(max = 3) String party_id;
    @NotEmpty @Size(max = 2) String country_code;
    @NotNull Role role;
    @NotNull ConnectionStatus status;
    @NotNull Instant last_updated;
}
