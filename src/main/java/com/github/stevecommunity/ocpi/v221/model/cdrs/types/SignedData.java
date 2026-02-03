package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class SignedData {
    @NotNull @Size(max = 36) String encoding_method;
    @NotNull Integer encoding_method_version;
    @Size(max = 512) String public_key;
    @NotNull @Size(max = 5120) String signed_values;
    @Size(max = 512) String url;
}
