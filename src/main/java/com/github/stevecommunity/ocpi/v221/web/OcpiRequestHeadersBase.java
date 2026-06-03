package com.github.stevecommunity.ocpi.v221.web;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.NotEmpty;

@Getter
@ToString
public class OcpiRequestHeadersBase {

    @NotEmpty String xRequestId;
    @NotEmpty String xCorrelationId;

    /**
     * To be used on server-side
     */
    public OcpiRequestHeadersBase(NativeWebRequest webRequest) {
        xRequestId = webRequest.getHeader(OcpiApi.HEADER_X_REQUEST_ID);
        xCorrelationId = webRequest.getHeader(OcpiApi.HEADER_X_CORRELATION_ID);
    }

    /**
     * Can be used by clients
     */
    @Builder(builderMethodName = "baseBuilder")
    public OcpiRequestHeadersBase(String xRequestId, String xCorrelationId) {
        this.xRequestId = requireNonEmpty(xRequestId, "xRequestId must not be null/empty");
        this.xCorrelationId = requireNonEmpty(xCorrelationId, "xCorrelationId must not be null/empty");
    }

    private static String requireNonEmpty(String obj, String message) {
        if (StringUtils.isEmpty(obj)) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }
}
