package com.github.stevecommunity.ocpi.v221.model.sessions;

import com.github.stevecommunity.ocpi.v221.model.Price;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.AuthMethod;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.CdrToken;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.ChargingPeriod;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.SessionStatus;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
public class SessionPatch {
    @Size(max = 2) String country_code;
    @Size(max = 3) String party_id;
    @Size(max = 36) String id;
    Instant start_date_time;
    Instant end_date_time;
    Double kwh;
    CdrToken cdr_token;
    AuthMethod auth_method;
    @Size(max = 36) String authorization_reference;
    @Size(max = 36) String location_id;
    @Size(max = 36) String evse_uid;
    @Size(max = 36) String connector_id;
    @Size(max = 255) String meter_id;
    @Size(max = 3) String currency;
    List<ChargingPeriod> charging_periods;
    Price total_cost;
    SessionStatus status;
    @NotNull Instant last_updated;
}
