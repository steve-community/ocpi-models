package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class EnvironmentalImpact {
    @NotNull EnvironmentalImpactCategory category;
    @NotNull Double amount;
}
