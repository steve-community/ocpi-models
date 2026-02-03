package com.github.stevecommunity.ocpi.v221.model.cdrs.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.stevecommunity.ocpi.v221.model.AbstractLocation;
import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorFormat;
import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorType;
import com.github.stevecommunity.ocpi.v221.model.locations.types.PowerType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CdrLocation extends AbstractLocation {
    @NotNull @Size(max = 36) String evse_uid;
    @NotNull @Size(max = 48) String evse_id;
    @NotNull @Size(max = 36) String connector_id;
    @NotNull ConnectorType connector_standard;
    @NotNull ConnectorFormat connector_format;
    @NotNull PowerType connector_power_type;
}
