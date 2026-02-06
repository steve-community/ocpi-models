package com.github.stevecommunity.ocpi.v221.web;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    SUCCESS(1000, "Generic success code"),

    CLIENT_ERROR(2000, "Generic client error"),
    INVALID_PARAMETERS(2001, "Invalid or missing parameters"),
    NOT_ENOUGH_INFORMATION(2002, "Not enough information"),
    UNKNOWN_LOCATION(2003, "Unknown Location"),
    UNKNOWN_TOKEN(2004, "Unknown Token"),

    SERVER_ERROR(3000, "Generic server error"),
    UNABLE_TO_USE_API_ERROR(3001, "Unable to use the client's API"),
    UNSUPPORTED_VERSION_ERROR(3002, "Unsupported version"),
    MISSION_ENDPOINT_ERROR(3003, "No matching endpoints or expected endpoints missing between parties"),

    HUB_ERROR(4000, "Generic hub error"),
    UNKNOWN_RECEIVER_HUB_ERROR(4001, "Unknown receiver (TO address is unknown)"),
    TIMED_OUT_FORWARDED_REQUEST_HUB_ERROR(4002, "Timeout on forwarded request (message is forwarded, but request times out)"),
    CONNECTION_PROBLEM_HUB_ERROR(4003, "Connection problem (receiving party is not connected)");

    @JsonValue
    private final int value;

    private final String reasonPhrase;
}
