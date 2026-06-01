package com.github.stevecommunity.ocpi.v221.web;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.Size;

@Getter
@ToString
public class OcpiRequestHeaders extends OcpiRequestHeadersBase {

    @Size(min = 2, max = 2) String fromCountryCode;
    @Size(min = 3, max = 3) String fromPartyId;
    @Size(min = 2, max = 2) String toCountryCode;
    @Size(min = 3, max = 3) String toPartyId;

    /**
     * To be used on server-side
     */
    public OcpiRequestHeaders(NativeWebRequest webRequest) {
        super(webRequest);
        fromCountryCode = webRequest.getHeader("OCPI-from-country-code");
        fromPartyId = webRequest.getHeader("OCPI-from-party-id");
        toCountryCode = webRequest.getHeader("OCPI-to-country-code");
        toPartyId = webRequest.getHeader("OCPI-to-party-id");
    }

    /**
     * Can be used by clients
     */
    @Builder
    public OcpiRequestHeaders(
        String xRequestId,
        String xCorrelationId,
        String fromCountryCode,
        String fromPartyId,
        String toCountryCode,
        String toPartyId
    ) {
        super(xRequestId, xCorrelationId);
        this.fromCountryCode = fromCountryCode;
        this.fromPartyId = fromPartyId;
        this.toCountryCode = toCountryCode;
        this.toPartyId = toPartyId;
    }
}
