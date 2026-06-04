package com.github.stevecommunity.ocpi.v221.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
@Data
public class OcpiResponseVoid {
    @NotNull StatusCode status_code;
    String status_message;
    @NotNull final Instant timestamp = Instant.now();

    public static OcpiResponseVoid from() {
        return new OcpiResponseVoid();
    }

    public static OcpiResponseVoid error(StatusCode sc, String sm) {
        return new OcpiResponseVoid()
            .setStatus_code(sc)
            .setStatus_message(sm);
    }

    public static ResponseEntity<OcpiResponseVoid> success(OcpiRequestHeadersBase headers) {
        return from()
            .setStatus_code(StatusCode.SUCCESS)
            .toOkResponse(headers);
    }

    public ResponseEntity<OcpiResponseVoid> toOkResponse(OcpiRequestHeadersBase headers) {
        return toResponse(HttpStatus.OK, headers);
    }

    public ResponseEntity<OcpiResponseVoid> toResponse(HttpStatus status, OcpiRequestHeadersBase headers) {
        return ResponseEntity
            .status(status)
            .header(OcpiApi.HEADER_X_REQUEST_ID, headers.getXRequestId())
            .header(OcpiApi.HEADER_X_CORRELATION_ID, headers.getXCorrelationId())
            .body(this);
    }
}
