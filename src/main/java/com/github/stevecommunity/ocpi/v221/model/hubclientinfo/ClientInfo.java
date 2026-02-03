package com.github.stevecommunity.ocpi.v221.model.hubclientinfo;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.Role;
import com.github.stevecommunity.ocpi.v221.model.hubclientinfo.types.ConnectionStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Data
public class ClientInfo {
    @NotNull @Size(max = 3) String party_id;
    @NotNull @Size(max = 2) String country_code;
    @NotNull Role role;
    @NotNull ConnectionStatus status;
    @NotNull Instant last_updated;
}
