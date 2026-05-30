package com.github.stevecommunity.ocpi.v221.web;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Accessors(chain = true)
@Data
public class OcpiResponse<T> {
    @NotNull final T data;
    @NotNull StatusCode status_code;
    String status_message;
    @NotNull final Instant timestamp = Instant.now();

    public static <T> OcpiResponse<T> from(@NotNull T data) {
        return new OcpiResponse<>(data);
    }

    public static OcpiResponseVoid from() {
        return new OcpiResponseVoid();
    }
}
