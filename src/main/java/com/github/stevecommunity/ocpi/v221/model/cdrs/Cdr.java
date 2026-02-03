package com.github.stevecommunity.ocpi.v221.model.cdrs;

import com.github.stevecommunity.ocpi.v221.model.Price;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.AuthMethod;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.CdrToken;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.ChargingPeriod;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.SignedData;
import com.github.stevecommunity.ocpi.v221.model.locations.Location;
import com.github.stevecommunity.ocpi.v221.model.payments.adjustments.PaymentsModule;
import com.github.stevecommunity.ocpi.v221.model.tariffs.Tariff;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
public class Cdr {
    @NotNull(groups = PaymentsModule.class) @Size(max = 36) String authorization_reference;

    @NotNull @Size(max = 2) String country_code;
    @NotNull @Size(max = 3) String party_id;
    @NotNull @Size(max = 39) String id;
    @NotNull Instant start_date_time;
    @NotNull Instant end_date_time;
    @Size(max = 36) String session_id;
    @NotNull CdrToken cdr_token;
    @NotNull AuthMethod auth_method;
    @NotNull Location cdr_location;
    @Size(max = 255) String meter_id;
    @NotNull @Size(max = 3) String currency;
    List<Tariff> tariffs;
    @NotEmpty List<ChargingPeriod> charging_periods;
    SignedData signed_data;
    @NotNull Price total_cost;
    Price total_fixed_cost;
    @NotNull Double total_energy;
    Price total_energy_cost;
    @NotNull Double total_time;
    Price total_time_cost;
    Double total_parking_time;
    Price total_parking_cost;
    Price total_reservation_cost;
    @Size(max = 255) String remark;
    @Size(max = 39) String invoice_reference_id;
    Boolean credit;
    @Size(max = 39) String credit_reference_id;
    Boolean home_charging_compensation;
    @NotNull Instant last_updated;
}
