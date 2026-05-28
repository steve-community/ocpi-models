package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class BusinessDetails {
    /**
     * Name of the operator.
     */
    @NotNull @Size(max = 100) String name;

    /**
     * Website of the operator.
     */
    @Size(max = 255) String website;

    /**
     * Logo of the operator.
     */
    Image logo;
}
