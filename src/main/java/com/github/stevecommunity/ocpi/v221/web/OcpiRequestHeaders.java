package com.github.stevecommunity.ocpi.v221.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@ToString
public class OcpiRequestHeaders {
    @NotNull String xRequestId;
    @NotNull String xCorrelationId;
    @Size(min = 2, max = 2) String fromCountryCode;
    @Size(min = 3, max = 3) String fromPartyId;
    @Size(min = 2, max = 2) String toCountryCode;
    @Size(min = 3, max = 3) String toPartyId;

    public static OcpiRequestHeaders fromRequestHeaders(NativeWebRequest webRequest) {
        OcpiRequestHeaders headers = new OcpiRequestHeaders();
        headers.setXRequestId(webRequest.getHeader("X-Request-ID"));
        headers.setXCorrelationId(webRequest.getHeader("X-Correlation-ID"));
        headers.setFromCountryCode(webRequest.getHeader("OCPI-from-country-code"));
        headers.setFromPartyId(webRequest.getHeader("OCPI-from-party-id"));
        headers.setToCountryCode(webRequest.getHeader("OCPI-to-country-code"));
        headers.setToPartyId(webRequest.getHeader("OCPI-to-party-id"));
        return headers;
    }
}
