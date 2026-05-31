package com.github.stevecommunity.ocpi.v221.model.cdrs;

import com.github.stevecommunity.ocpi.v221.model.Price;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.AuthMethod;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.CdrLocation;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.CdrToken;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.ChargingPeriod;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.SignedData;
import com.github.stevecommunity.ocpi.v221.model.tariffs.Tariff;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
public class Cdr {
    @NotEmpty @Size(max = 2) String country_code;
    @NotEmpty @Size(max = 3) String party_id;
    @NotEmpty @Size(max = 39) String id;
    @NotNull Instant start_date_time;
    @NotNull Instant end_date_time;
    @Size(max = 36) String session_id;
    @NotNull @Valid CdrToken cdr_token;
    @NotNull AuthMethod auth_method;
    @Size(max = 36) String authorization_reference;
    @NotNull @Valid CdrLocation cdr_location;
    @Size(max = 255) String meter_id;
    @NotEmpty @Size(max = 3) String currency;
    @Valid List<Tariff> tariffs;
    @NotEmpty @Valid List<ChargingPeriod> charging_periods;
    @Valid SignedData signed_data;
    @NotNull @Valid Price total_cost;
    @Valid Price total_fixed_cost;
    @NotNull Double total_energy;
    @Valid Price total_energy_cost;
    @NotNull Double total_time;
    @Valid Price total_time_cost;
    Double total_parking_time;
    @Valid Price total_parking_cost;
    @Valid Price total_reservation_cost;
    @Size(max = 255) String remark;
    @Size(max = 39) String invoice_reference_id;
    Boolean credit;
    @Size(max = 39) String credit_reference_id;
    Boolean home_charging_compensation;
    @NotNull Instant last_updated;
}
