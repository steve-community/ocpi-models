package com.github.stevecommunity.ocpi.v221.model.locations.types;

import com.github.stevecommunity.ocpi.v221.util.OcpiDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Accessors(chain = true)
@Data
public class StatusSchedule {
    @OcpiDateTime @NotNull Instant period_begin;
    @OcpiDateTime Instant period_end;
    @NotNull Status status;
}
