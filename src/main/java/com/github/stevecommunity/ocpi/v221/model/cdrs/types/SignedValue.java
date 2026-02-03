package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class SignedValue {
    @NotNull @Size(max = 32) String nature;
    @NotNull @Size(max = 512) String plain_data;
    @NotNull @Size(max = 5000) String signed_data;
}
