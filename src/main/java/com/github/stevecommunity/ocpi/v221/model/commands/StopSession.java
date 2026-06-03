package com.github.stevecommunity.ocpi.v221.model.commands;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Accessors(chain = true)
@Data
public class StopSession {
    @NotEmpty @Size(max = 255) String response_url;
    @NotEmpty @Size(max = 36) String session_id;
}
