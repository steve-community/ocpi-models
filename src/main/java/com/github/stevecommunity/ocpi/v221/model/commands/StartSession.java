package com.github.stevecommunity.ocpi.v221.model.commands;

import com.github.stevecommunity.ocpi.v221.model.tokens.Token;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Accessors(chain = true)
@Data
public class StartSession {
    @NotEmpty @Size(max = 255) String response_url;
    @NotNull @Valid Token token;
    @NotEmpty @Size(max = 36) String location_id;
    @Size(max = 36) String evse_uid;
    @Size(max = 36) String connector_id;
    @Size(max = 36) String authorization_reference;

}
