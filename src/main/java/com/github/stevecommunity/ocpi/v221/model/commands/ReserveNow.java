package com.github.stevecommunity.ocpi.v221.model.commands;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.tokens.Token;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Data
public class ReserveNow {
    @NotNull @Size(max = 255) String response_url;
    @NotNull Token token;
    @NotNull Instant expiry_date;
    @NotNull @Size(max = 36) String reservation_id;
    @NotNull @Size(max = 36) String location_id;
    @Size(max = 36) String evse_uid;
    @Size(max = 36) String authorization_reference;
}
