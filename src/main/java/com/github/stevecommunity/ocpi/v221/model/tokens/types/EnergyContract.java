package com.github.stevecommunity.ocpi.v221.model.tokens.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class EnergyContract {
    @NotNull @Size(max = 64) String supplier_name;
    @Size(max = 64) String contract_id;
}
