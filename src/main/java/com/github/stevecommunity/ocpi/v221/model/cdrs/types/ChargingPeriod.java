package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Accessors(chain = true)
@Data
public class ChargingPeriod {
    @NotNull Instant start_date_time;
    @NotEmpty @Valid List<CdrDimension> dimensions;
    @Size(max = 36) String tariff_id;
}
