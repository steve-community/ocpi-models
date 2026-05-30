package com.github.stevecommunity.ocpi.v221.web;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Accessors(chain = true)
@Data
public class OcpiResponseVoid {
    @NotNull StatusCode status_code;
    String status_message;
    @NotNull final Instant timestamp = Instant.now();
}
