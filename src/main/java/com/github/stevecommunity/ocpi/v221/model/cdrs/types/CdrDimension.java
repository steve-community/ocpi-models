package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class CdrDimension {
    @NotNull CdrDimensionType type;
    @NotNull Double volume;
}
