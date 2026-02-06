package com.github.stevecommunity.ocpi.v221.web;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
public class OcpiResponse<T> {
    T data;
    @NotNull StatusCode status_code;
    String status_message;
    final Instant timestamp = Instant.now();
}
