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
    List<@Valid RegularHours> regular_hours;
    List<@Valid ExceptionalPeriod> exceptional_openings;
    List<@Valid ExceptionalPeriod> exceptional_closings;
}
