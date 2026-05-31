package com.github.stevecommunity.ocpi.v221.model.tariffs.types;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class TariffElement {
    @NotEmpty @Valid List<PriceComponent> price_components;
    @Valid TariffRestriction restrictions;
}
