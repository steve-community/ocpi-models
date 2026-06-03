package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Accessors(chain = true)
@Data
public class StatusSchedule {
    @NotNull Instant period_begin;
    Instant period_end;
    @NotNull Status status;
}
