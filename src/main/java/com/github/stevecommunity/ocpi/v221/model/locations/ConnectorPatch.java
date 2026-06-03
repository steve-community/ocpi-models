package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorFormat;
import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorType;
import com.github.stevecommunity.ocpi.v221.model.locations.types.PowerType;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Accessors(chain = true)
@Data
public class ConnectorPatch {
    @Size(max = 36) String id;
    ConnectorType standard;
    ConnectorFormat format;
    PowerType power_type;
    Integer max_voltage;
    Integer max_amperage;
    Integer max_electric_power;
    List<@Size(max = 36) String> tariff_ids;
    @Size(max = 255) String terms_and_conditions;
    @NotNull Instant last_updated;
}
