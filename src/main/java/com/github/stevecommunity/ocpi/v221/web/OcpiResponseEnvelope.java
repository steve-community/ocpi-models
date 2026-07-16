package com.github.stevecommunity.ocpi.v221.web;

import java.time.Instant;

/**
 * Common fields present in every OCPI response envelope.
 */
public interface OcpiResponseEnvelope {
    StatusCode getStatus_code();
    String getStatus_message();
    Instant getTimestamp();
}
