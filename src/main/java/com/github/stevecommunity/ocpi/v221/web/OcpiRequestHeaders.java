package com.github.stevecommunity.ocpi.v221.web;

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

    public OcpiRequestHeaders(NativeWebRequest webRequest) {
        super(webRequest);
        fromCountryCode = webRequest.getHeader("OCPI-from-country-code");
        fromPartyId = webRequest.getHeader("OCPI-from-party-id");
        toCountryCode = webRequest.getHeader("OCPI-to-country-code");
        toPartyId = webRequest.getHeader("OCPI-to-party-id");
    }
}
