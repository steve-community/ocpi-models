package com.github.stevecommunity.ocpi.v221.model.sessions;

import com.github.stevecommunity.ocpi.v221.model.cdrs.types.AuthMethod;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.CdrToken;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.SessionStatus;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public interface SessionNullChecks {
    @NotNull String getCountry_code();
    @NotNull String getParty_id();
    @NotNull String getId();
    @NotNull Instant getStart_date_time();
    @NotNull Double getKwh();
    @NotNull CdrToken getCdr_token();
    @NotNull AuthMethod getAuth_method();
    @NotNull String getLocation_id();
    @NotNull String getEvse_uid();
    @NotNull String getConnector_id();
    @NotNull String getCurrency();
    @NotNull SessionStatus getStatus();
}
