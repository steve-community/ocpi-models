package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.sessions.Session;
import com.github.stevecommunity.ocpi.v221.model.sessions.SessionPatch;
import com.github.stevecommunity.ocpi.v221.web.OcpiApi;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = OcpiApi.SESSIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public interface SessionsReceiverApi extends OcpiApi.Sessions.Receiver {

    @GetMapping("/{country_code}/{party_id}/{session_id}")
    default ResponseEntity<OcpiResponse<Session>> getSession(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("session_id") @Size(min = 1, max = 36) String sessionId
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PutMapping(value = "/{country_code}/{party_id}/{session_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> putSession(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("session_id") @Size(min = 1, max = 36) String sessionId,
        @Valid @RequestBody Session session
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PatchMapping(value = "/{country_code}/{party_id}/{session_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> patchSession(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("session_id") @Size(min = 1, max = 36) String sessionId,
        @Valid @RequestBody SessionPatch session
    ) {
        throw new RuntimeException("Not implemented");
    }
}
