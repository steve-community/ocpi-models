package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ActiveChargingProfileResult;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ChargingProfileResult;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.ClearProfileResult;
import com.github.stevecommunity.ocpi.v221.model.chargingprofiles.types.ActiveChargingProfile;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@RequestMapping(value = "/ocpi/2.2.1/chargingprofiles/response", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ChargingProfilesSenderApi {

    @PostMapping(value = "/active", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> postActiveChargingProfileResult(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @Valid @RequestBody ActiveChargingProfileResult result
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PostMapping(value = "/set", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> postChargingProfileResult(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @Valid @RequestBody ChargingProfileResult result
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PostMapping(value = "/clear", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> postClearProfileResult(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @Valid @RequestBody ClearProfileResult result
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PutMapping(value = "/{session_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> putActiveChargingProfile(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("session_id") @Size(min = 1, max = 36) String sessionId,
        @Valid @RequestBody ActiveChargingProfile activeChargingProfile
    ) {
        throw new RuntimeException("Not implemented");
    }
}
