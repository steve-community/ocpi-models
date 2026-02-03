package com.github.stevecommunity.ocpi.v221.model.locations;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.stevecommunity.ocpi.v221.model.AbstractLocation;
import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.locations.types.AdditionalGeoLocation;
import com.github.stevecommunity.ocpi.v221.model.locations.types.BusinessDetails;
import com.github.stevecommunity.ocpi.v221.model.locations.types.EnergyMix;
import com.github.stevecommunity.ocpi.v221.model.locations.types.Facility;
import com.github.stevecommunity.ocpi.v221.model.locations.types.Hours;
import com.github.stevecommunity.ocpi.v221.model.locations.types.Image;
import com.github.stevecommunity.ocpi.v221.model.locations.types.ParkingType;
import com.github.stevecommunity.ocpi.v221.model.locations.types.PublishTokenType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Location extends AbstractLocation {
    @NotNull @Size(max = 2) String country_code;
    @NotNull @Size(max = 3) String party_id;
    @NotNull Boolean publish;
    List<PublishTokenType> publish_allowed_to;
    List<AdditionalGeoLocation> related_locations;
    ParkingType parking_type;
    List<Evse> evses;
    List<DisplayText> directions;
    BusinessDetails operator;
    BusinessDetails suboperator;
    BusinessDetails owner;
    List<Facility> facilities;
    @NotNull @Size(max = 255) String time_zone;
    Hours opening_times;
    Boolean charging_when_closed;
    List<Image> images;
    EnergyMix energy_mix;
    @NotNull Instant last_updated;
}
