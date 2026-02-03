package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Data
public class SignedData {
    @NotNull @Size(max = 36) String encoding_method;
    Integer encoding_method_version;
    @Size(max = 512) String public_key;
    @NotEmpty List<SignedValue> signed_values;
    @Size(max = 512) String url;
}
