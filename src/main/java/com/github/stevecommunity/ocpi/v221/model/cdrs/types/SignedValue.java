package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class SignedValue {
    @NotEmpty @Size(max = 32) String nature;
    @NotEmpty @Size(max = 512) String plain_data;
    @NotEmpty @Size(max = 5000) String signed_data;
}
