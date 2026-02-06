package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.sessions.ChargingPreferences;
import com.github.stevecommunity.ocpi.v221.model.sessions.Session;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.ChargingPreferencesResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.List;

@RequestMapping(value = "/ocpi/2.2.1/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SessionsSenderApi {

    @GetMapping
    default ResponseEntity<OcpiResponse<List<Session>>> getSessions(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @Valid @ParameterObject OcpiRequestParameters params
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PutMapping(value = "/{session_id}/charging_preferences", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponse<ChargingPreferencesResponse>> putChargingPreferences(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("session_id") @Size(min = 1, max = 36) String sessionId,
        @Valid @RequestBody ChargingPreferences chargingPreferences
    ) {
        throw new RuntimeException("Not implemented");
    }
}
