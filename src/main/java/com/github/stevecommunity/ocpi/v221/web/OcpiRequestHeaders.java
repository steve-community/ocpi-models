package com.github.stevecommunity.ocpi.v221.web;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.Size;
import java.util.UUID;

@Getter
@ToString
public class OcpiRequestHeaders extends OcpiRequestHeadersBase {

    @Size(min = 2, max = 2) String fromCountryCode;
    @Size(min = 3, max = 3) String fromPartyId;
    @Size(min = 2, max = 2) String toCountryCode;
    @Size(min = 3, max = 3) String toPartyId;

    public OcpiRequestHeaders(NativeWebRequest webRequest) {
        super(webRequest);
        fromCountryCode = webRequest.getHeader(OcpiApi.HEADER_OCPI_FROM_COUNTRY);
        fromPartyId = webRequest.getHeader(OcpiApi.HEADER_OCPI_FROM_PARTY_ID);
        toCountryCode = webRequest.getHeader(OcpiApi.HEADER_OCPI_TO_COUNTRY);
        toPartyId = webRequest.getHeader(OcpiApi.HEADER_OCPI_TO_PARTY_ID);
    }

    @Builder
    private OcpiRequestHeaders(String fromCountryCode, String fromPartyId, String toCountryCode, String toPartyId) {
        super(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        this.fromCountryCode = fromCountryCode;
        this.fromPartyId = fromPartyId;
        this.toCountryCode = toCountryCode;
        this.toPartyId = toPartyId;
    }
}
