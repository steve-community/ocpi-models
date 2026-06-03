package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Accessors(chain = true)
@Data
public class EnergyMix {
    @NotNull Boolean is_green_energy;
    @Valid List<EnergySource> energy_sources;
    @Valid List<EnvironmentalImpact> environ_impact;
    @Size(max = 64) String supplier_name;
    @Size(max = 64) String energy_product_name;
}
