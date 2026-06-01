package com.github.stevecommunity.ocpi.v221.model.sessions;

import com.github.stevecommunity.ocpi.v221.model.cdrs.types.AuthMethod;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.CdrToken;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.SessionStatus;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public interface SessionNullChecks {
    @NotEmpty String getCountry_code();
    @NotEmpty String getParty_id();
    @NotEmpty String getId();
    @NotNull Instant getStart_date_time();
    @NotNull Double getKwh();
    @NotNull CdrToken getCdr_token();
    @NotNull AuthMethod getAuth_method();
    @NotEmpty String getLocation_id();
    @NotEmpty String getEvse_uid();
    @NotEmpty String getConnector_id();
    @NotEmpty String getCurrency();
    @NotNull SessionStatus getStatus();
}
