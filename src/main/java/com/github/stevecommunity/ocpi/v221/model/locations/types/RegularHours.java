package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.github.stevecommunity.ocpi.v221.util.ValidationPatterns.TIME_HHMM;

@Data
public class RegularHours {
    @NotNull Integer weekday;
    @NotEmpty @Pattern(regexp = TIME_HHMM) String period_begin;
    @NotEmpty @Pattern(regexp = TIME_HHMM) String period_end;
}
