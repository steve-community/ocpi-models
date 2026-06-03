package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;

@Accessors(chain = true)
@Data
public class EnergySource {
    @NotNull EnergySourceCategory source;
    @NotNull Double percentage;
}
