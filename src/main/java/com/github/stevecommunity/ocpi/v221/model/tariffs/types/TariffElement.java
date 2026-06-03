package com.github.stevecommunity.ocpi.v221.model.tariffs.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Accessors(chain = true)
@Data
public class TariffElement {
    @NotEmpty @Valid List<PriceComponent> price_components;
    @Valid TariffRestriction restrictions;
}
