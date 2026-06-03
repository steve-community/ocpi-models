package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Accessors(chain = true)
@Data
public class Hours {
    @NotNull Boolean twentyfourseven;
    @Valid List<RegularHours> regular_hours;
    @Valid List<ExceptionalPeriod> exceptional_openings;
    @Valid List<ExceptionalPeriod> exceptional_closings;
}
