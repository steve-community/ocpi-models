package com.github.stevecommunity.ocpi.v221.model.tokens.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Accessors(chain = true)
@Data
public class EnergyContract {
    @NotEmpty @Size(max = 64) String supplier_name;
    @Size(max = 64) String contract_id;
}
