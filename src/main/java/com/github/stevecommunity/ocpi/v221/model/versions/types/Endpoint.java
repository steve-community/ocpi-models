package com.github.stevecommunity.ocpi.v221.model.versions.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Accessors(chain = true)
@Data
public class Endpoint {
    @NotNull ModuleID identifier;
    @NotNull InterfaceRole role;
    @NotEmpty @Size(max = 255) String url;
}
