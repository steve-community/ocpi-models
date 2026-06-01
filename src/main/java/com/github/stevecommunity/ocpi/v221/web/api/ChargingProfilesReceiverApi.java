package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ChargingProfileResponse;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.SetChargingProfile;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = "/ocpi/2.2.1/chargingprofiles", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ChargingProfilesReceiverApi {

    @GetMapping("/{session_id}")
    default ResponseEntity<OcpiResponse<ChargingProfileResponse>> getActiveChargingProfile(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @PathVariable("session_id") @Size(min = 1, max = 36) String sessionId,
        @RequestParam @Min(0) Integer duration,
        @RequestParam("response_url") String responseUrl
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PutMapping(value = "/{session_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponse<ChargingProfileResponse>> putChargingProfile(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @PathVariable("session_id") @Size(min = 1, max = 36) String sessionId,
        @Valid @RequestBody SetChargingProfile chargingProfile
    ) {
        throw new RuntimeException("Not implemented");
    }

    @DeleteMapping("/{session_id}")
    default ResponseEntity<OcpiResponse<ChargingProfileResponse>> deleteChargingProfile(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @PathVariable("session_id") @Size(min = 1, max = 36) String sessionId,
        @RequestParam("response_url") String responseUrl
    ) {
        throw new RuntimeException("Not implemented");
    }
}
