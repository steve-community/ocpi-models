package com.github.stevecommunity.ocpi.v221.model.tariffs.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;

@Accessors(chain = true)
@Data
public class PriceComponent {
    @NotNull TariffDimensionType type;
    @NotNull Double price;
    Double vat;
    @NotNull Integer step_size;
}
