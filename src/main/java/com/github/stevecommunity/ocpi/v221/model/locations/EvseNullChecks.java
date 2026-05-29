package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.locations.types.Status;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface EvseNullChecks {
    @NotNull String getUid();
    @NotNull Status getStatus();
    @NotEmpty List<Connector> getConnectors();
}
