package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.locations.types.Capability;
import com.github.stevecommunity.ocpi.v221.model.locations.types.GeoLocation;
import com.github.stevecommunity.ocpi.v221.model.locations.types.Image;
import com.github.stevecommunity.ocpi.v221.model.locations.types.ParkingRestriction;
import com.github.stevecommunity.ocpi.v221.model.locations.types.Status;
import com.github.stevecommunity.ocpi.v221.model.locations.types.StatusSchedule;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Accessors(chain = true)
@Data
public class EvsePatch {
    @Size(max = 36) String uid;
    @Size(max = 48) String evse_id;
    Status status;
    @Valid List<StatusSchedule> status_schedule;
    List<Capability> capabilities;
    @Valid List<Connector> connectors;
    @Size(max = 4) String floor_level;
    @Valid GeoLocation coordinates;
    @Size(max = 16) String physical_reference;
    @Valid List<DisplayText> directions;
    List<ParkingRestriction> parking_restrictions;
    @Valid List<Image> images;
    @NotNull Instant last_updated;
}
