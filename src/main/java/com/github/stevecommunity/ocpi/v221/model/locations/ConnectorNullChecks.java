package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorFormat;
import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorType;
import com.github.stevecommunity.ocpi.v221.model.locations.types.PowerType;

import jakarta.validation.constraints.NotNull;

public interface ConnectorNullChecks {
    @NotNull String getId();
    @NotNull ConnectorType getStandard();
    @NotNull ConnectorFormat getFormat();
    @NotNull PowerType getPower_type();
    @NotNull Integer getMax_voltage();
    @NotNull Integer getMax_amperage();
}
