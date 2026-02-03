package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
public class ChargingPeriod {
    @NotNull Instant start_date_time;
    @NotEmpty List<CdrDimension> dimensions;
    String tariff_id;
}
