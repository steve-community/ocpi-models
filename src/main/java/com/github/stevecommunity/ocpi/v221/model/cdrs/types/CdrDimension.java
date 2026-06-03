package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;

@Accessors(chain = true)
@Data
public class CdrDimension {
    @NotNull CdrDimensionType type;
    @NotNull Double volume;
}
