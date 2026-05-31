package com.github.stevecommunity.ocpi.v221.model.commands;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class UnlockConnector {
    @NotEmpty @Size(max = 255) String response_url;
    @NotEmpty @Size(max = 36) String location_id;
    @NotEmpty @Size(max = 36) String evse_uid;
    @NotEmpty @Size(max = 36) String connector_id;
}
