package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.locations.types.Capability;
import com.github.stevecommunity.ocpi.v221.model.locations.types.GeoLocation;
import com.github.stevecommunity.ocpi.v221.model.locations.types.Image;
import com.github.stevecommunity.ocpi.v221.model.locations.types.ParkingRestriction;
import com.github.stevecommunity.ocpi.v221.model.locations.types.Status;
import com.github.stevecommunity.ocpi.v221.model.locations.types.StatusSchedule;
import com.github.stevecommunity.ocpi.v221.model.payments.adjustments.PaymentsModule;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
public class Evse {
    @NotNull(groups = PaymentsModule.class) @Size(max = 16) String physical_reference;

    @NotNull @Size(max = 36) String uid;
    @Size(max = 48) String evse_id;
    @NotNull Status status;
    List<StatusSchedule> status_schedule;
    List<Capability> capabilities;
    @NotEmpty List<Connector> connectors;
    @Size(max = 4) String floor_level;
    GeoLocation coordinates;
    List<DisplayText> directions;
    List<ParkingRestriction> parking_restrictions;
    List<Image> images;
    @NotNull Instant last_updated;
}
