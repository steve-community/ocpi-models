package com.github.stevecommunity.ocpi.v221.model.commands;

import com.github.stevecommunity.ocpi.v221.model.tokens.Token;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Data
public class ReserveNow {
    @NotEmpty @Size(max = 255) String response_url;
    @NotNull @Valid Token token;
    @NotNull Instant expiry_date;
    @NotEmpty @Size(max = 36) String reservation_id;
    @NotEmpty @Size(max = 36) String location_id;
    @Size(max = 36) String evse_uid;
    @Size(max = 36) String authorization_reference;
}
