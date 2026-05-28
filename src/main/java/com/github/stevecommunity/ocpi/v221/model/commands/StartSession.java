package com.github.stevecommunity.ocpi.v221.model.commands;

import com.github.stevecommunity.ocpi.v221.model.tokens.Token;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class StartSession {
    @NotNull @Size(max = 255) String response_url;
    @NotNull Token token;
    @NotNull @Size(max = 36) String location_id;
    @Size(max = 36) String evse_uid;
    @Size(max = 36) String connector_id;
    @Size(max = 36) String authorization_reference;

}
