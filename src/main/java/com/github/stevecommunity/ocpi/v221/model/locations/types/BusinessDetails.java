package com.github.stevecommunity.ocpi.v221.model.locations.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Accessors(chain = true)
@Data
public class BusinessDetails {
    /**
     * Name of the operator.
     */
    @NotEmpty @Size(max = 100) String name;

    /**
     * Website of the operator.
     */
    @Size(max = 255) String website;

    /**
     * Logo of the operator.
     */
    @Valid Image logo;
}
