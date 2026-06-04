package com.github.stevecommunity.ocpi.v221.web;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.NotEmpty;

@Getter
@ToString
public class OcpiRequestHeadersBase {

    @NotEmpty String xRequestId;
    @NotEmpty String xCorrelationId;

    public OcpiRequestHeadersBase(NativeWebRequest webRequest) {
        xRequestId = webRequest.getHeader(OcpiApi.HEADER_X_REQUEST_ID);
        xCorrelationId = webRequest.getHeader(OcpiApi.HEADER_X_CORRELATION_ID);
    }
}
