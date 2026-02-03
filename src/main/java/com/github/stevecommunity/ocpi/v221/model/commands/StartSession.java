package com.github.stevecommunity.ocpi.v221.model.commands;

import com.github.stevecommunity.ocpi.v221.model.payments.adjustments.PaymentsModule;
import com.github.stevecommunity.ocpi.v221.model.tokens.Token;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class StartSession {
    @NotNull(groups = PaymentsModule.class) @Size(max = 36) String evse_uid;
    @NotNull(groups = PaymentsModule.class) @Size(max = 36) String connector_id;
    @NotNull(groups = PaymentsModule.class) @Size(max = 36) String authorization_reference;

    @NotNull @Size(max = 255) String response_url;
    @NotNull Token token;
    @NotNull @Size(max = 36) String location_id;
}
