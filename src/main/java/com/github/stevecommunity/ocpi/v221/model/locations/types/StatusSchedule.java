package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class StatusSchedule {
    @NotNull Instant period_begin;
    Instant period_end;
    @NotNull Status status;
}
