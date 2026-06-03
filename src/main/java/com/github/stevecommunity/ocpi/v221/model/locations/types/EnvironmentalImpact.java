package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;

@Accessors(chain = true)
@Data
public class EnvironmentalImpact {
    @NotNull EnvironmentalImpactCategory category;
    @NotNull Double amount;
}
