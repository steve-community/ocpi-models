package com.github.stevecommunity.ocpi.v221.model.sessions;

import com.github.stevecommunity.ocpi.v221.model.Price;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.AuthMethod;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.CdrToken;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.ChargingPeriod;
import com.github.stevecommunity.ocpi.v221.model.payments.adjustments.PaymentsModule;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.SessionStatus;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
public class Session {
    @NotNull(groups = PaymentsModule.class) @Size(max = 36) String authorization_reference;

    @NotNull @Size(max = 2) String country_code;
    @NotNull @Size(max = 3) String party_id;
    @NotNull @Size(max = 36) String id;
    @NotNull Instant start_date_time;
    Instant end_date_time;
    @NotNull Double kwh;
    @NotNull CdrToken cdr_token;
    @NotNull AuthMethod auth_method;
    @NotNull @Size(max = 36) String location_id;
    @NotNull @Size(max = 36) String evse_uid;
    @NotNull @Size(max = 36) String connector_id;
    @Size(max = 255) String meter_id;
    @NotNull @Size(max = 3) String currency;
    List<ChargingPeriod> charging_periods;
    Price total_cost;
    @NotNull SessionStatus status;
    @NotNull Instant last_updated;
}
