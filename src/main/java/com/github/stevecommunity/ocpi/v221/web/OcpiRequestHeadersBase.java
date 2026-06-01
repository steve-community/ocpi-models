package com.github.stevecommunity.ocpi.v221.web;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.NotNull;

@Getter
@ToString
public class OcpiRequestHeadersBase {

    @NotNull String xRequestId;
    @NotNull String xCorrelationId;

    public OcpiRequestHeadersBase(NativeWebRequest webRequest) {
        xRequestId = webRequest.getHeader("X-Request-ID");
        xCorrelationId = webRequest.getHeader("X-Correlation-ID");
    }
}
