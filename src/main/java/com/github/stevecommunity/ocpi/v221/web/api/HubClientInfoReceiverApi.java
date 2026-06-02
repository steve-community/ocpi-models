package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.hubclientinfo.ClientInfo;
import com.github.stevecommunity.ocpi.v221.web.OcpiApi;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeadersBase;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = OcpiApi.HUB_CLIENT_INFO_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public interface HubClientInfoReceiverApi extends OcpiApi.Receiver.HubClientInfo {

    @GetMapping("/{country_code}/{party_id}")
    default ResponseEntity<OcpiResponse<ClientInfo>> getClientInfo(
        @Parameter(hidden = true) @Valid OcpiRequestHeadersBase headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PutMapping(value = "/{country_code}/{party_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> putClientInfo(
        @Parameter(hidden = true) @Valid OcpiRequestHeadersBase headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @Valid @RequestBody ClientInfo clientInfo
    ) {
        throw new RuntimeException("Not implemented");
    }
}
