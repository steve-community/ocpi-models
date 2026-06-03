package com.github.stevecommunity.ocpi.v221.model;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;

@Accessors(chain = true)
@Data
public class Price {
    @NotNull Double excl_vat;
    Double incl_vat;
}
