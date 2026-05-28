package com.github.stevecommunity.ocpi.v221.model.versions.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class Endpoint {
    @NotNull ModuleID identifier;
    @NotNull InterfaceRole role;
    @NotNull @Size(max = 255) String url;
}
