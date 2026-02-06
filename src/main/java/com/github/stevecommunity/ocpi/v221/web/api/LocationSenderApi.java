package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.locations.Connector;
import com.github.stevecommunity.ocpi.v221.model.locations.Evse;
import com.github.stevecommunity.ocpi.v221.model.locations.Location;
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
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.List;

@RequestMapping(value = "/ocpi/2.2.1/locations", produces = MediaType.APPLICATION_JSON_VALUE)
public interface LocationSenderApi {

    @GetMapping
    default ResponseEntity<OcpiResponse<List<Location>>> getLocations(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @Valid @ParameterObject OcpiRequestParameters params
    ) {
        throw new RuntimeException("Not implemented");
    }

    @GetMapping("/{location_id}")
    default ResponseEntity<OcpiResponse<Location>> getLocation(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId
    ) {
        throw new RuntimeException("Not implemented");
    }

    @GetMapping("/{location_id}/{evse_uid}")
    default ResponseEntity<OcpiResponse<Evse>> getEvse(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId,
        @PathVariable("evse_uid") @Size(min = 1, max = 36) String evseUid
    ) {
        throw new RuntimeException("Not implemented");
    }

    @GetMapping("/{location_id}/{evse_uid}/{connector_id}")
    default ResponseEntity<OcpiResponse<Connector>> getConnector(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId,
        @PathVariable("evse_uid") @Size(min = 1, max = 36) String evseUid,
        @PathVariable("connector_id") @Size(min = 1, max = 36) String connectorId
    ) {
        throw new RuntimeException("Not implemented");
    }
}
