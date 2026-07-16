package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.web.StatusCode;
import lombok.Getter;
import org.springframework.web.client.RestClientException;

/**
 * Raised when an HTTP-successful response is not a successful OCPI response.
 */
@Getter
public class OcpiResponseException extends RestClientException {

    private final StatusCode statusCode;
    private final String statusMessage;

    OcpiResponseException(StatusCode statusCode, String statusMessage) {
        super(message(statusCode, statusMessage));
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    private static String message(StatusCode statusCode, String statusMessage) {
        String message = "OCPI request failed with status_code " + statusCode.getValue() + " (" + statusCode.name() + ")";

        return (statusMessage == null || statusMessage.isBlank())
            ? message
            : message + ": " + statusMessage;
    }
}
