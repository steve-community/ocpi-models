package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorFormat;
import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorType;
import com.github.stevecommunity.ocpi.v221.model.locations.types.PowerType;
import com.github.stevecommunity.ocpi.v221.model.payments.adjustments.PaymentsModule;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
public class Connector {
    @NotEmpty(groups = PaymentsModule.class) List<@Size(max = 36) String> tariff_ids;

    @NotNull @Size(max = 36) String id;
    @NotNull ConnectorType standard;
    @NotNull ConnectorFormat format;
    @NotNull PowerType power_type;
    @NotNull Integer max_voltage;
    @NotNull Integer max_amperage;
    Integer max_electric_power;
    @Size(max = 255) String terms_and_conditions;
    @NotNull Instant last_updated;
}
