package com.github.stevecommunity.ocpi.v221.model.locations.types;

import com.github.stevecommunity.ocpi.v221.util.OcpiDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Accessors(chain = true)
@Data
public class ExceptionalPeriod {
    @OcpiDateTime @NotNull Instant period_begin;
    @OcpiDateTime @NotNull Instant period_end;
}
