package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class Hours {
    @NotNull Boolean twentyfourseven;
    List<RegularHours> regular_hours;
    List<ExceptionalPeriod> exceptional_openings;
    List<ExceptionalPeriod> exceptional_closings;
}
