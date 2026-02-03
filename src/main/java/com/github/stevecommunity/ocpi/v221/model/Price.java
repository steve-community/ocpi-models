package com.github.stevecommunity.ocpi.v221.model;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class Price {
    @NotNull Double excl_vat;
    Double incl_vat;
}
