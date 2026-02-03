package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class ExceptionalPeriod {
    @NotNull Instant period_begin;
    @NotNull Instant period_end;
}
