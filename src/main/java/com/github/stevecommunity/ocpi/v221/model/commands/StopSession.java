package com.github.stevecommunity.ocpi.v221.model.commands;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class StopSession {
    @NotNull @Size(max = 255) String response_url;
    @NotNull @Size(max = 36) String session_id;
}
