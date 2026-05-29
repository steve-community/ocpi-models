package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.locations.Connector;
import com.github.stevecommunity.ocpi.v221.model.locations.ConnectorPatch;
import com.github.stevecommunity.ocpi.v221.model.locations.Evse;
import com.github.stevecommunity.ocpi.v221.model.locations.EvsePatch;
import com.github.stevecommunity.ocpi.v221.model.locations.Location;
import com.github.stevecommunity.ocpi.v221.model.locations.LocationPatch;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = "/ocpi/2.2.1/locations", produces = MediaType.APPLICATION_JSON_VALUE)
public interface LocationReceiverApi {

    @GetMapping("/{country_code}/{party_id}/{location_id}")
    default ResponseEntity<OcpiResponse<Location>> getLocation(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PutMapping(value = "/{country_code}/{party_id}/{location_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> putLocation(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId,
        @Valid @RequestBody Location location
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PatchMapping(value = "/{country_code}/{party_id}/{location_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> patchLocation(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId,
        @Valid @RequestBody LocationPatch location
    ) {
        throw new RuntimeException("Not implemented");
    }

    @GetMapping("/{country_code}/{party_id}/{location_id}/{evse_uid}")
    default ResponseEntity<OcpiResponse<Evse>> getEvse(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId,
        @PathVariable("evse_uid") @Size(min = 1, max = 36) String evseUid
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PutMapping(value = "/{country_code}/{party_id}/{location_id}/{evse_uid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> putEvse(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId,
        @PathVariable("evse_uid") @Size(min = 1, max = 36) String evseUid,
        @Valid @RequestBody Evse evse
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PatchMapping(value = "/{country_code}/{party_id}/{location_id}/{evse_uid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> patchEvse(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId,
        @PathVariable("evse_uid") @Size(min = 1, max = 36) String evseUid,
        @Valid @RequestBody EvsePatch evse
    ) {
        throw new RuntimeException("Not implemented");
    }

    @GetMapping("/{country_code}/{party_id}/{location_id}/{evse_uid}/{connector_id}")
    default ResponseEntity<OcpiResponse<Connector>> getConnector(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId,
        @PathVariable("evse_uid") @Size(min = 1, max = 36) String evseUid,
        @PathVariable("connector_id") @Size(min = 1, max = 36) String connectorId
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PutMapping(value = "/{country_code}/{party_id}/{location_id}/{evse_uid}/{connector_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> putConnector(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId,
        @PathVariable("evse_uid") @Size(min = 1, max = 36) String evseUid,
        @PathVariable("connector_id") @Size(min = 1, max = 36) String connectorId,
        @Valid @RequestBody Connector connector
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PatchMapping(value = "/{country_code}/{party_id}/{location_id}/{evse_uid}/{connector_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> patchConnector(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("location_id") @Size(min = 1, max = 36) String locationId,
        @PathVariable("evse_uid") @Size(min = 1, max = 36) String evseUid,
        @PathVariable("connector_id") @Size(min = 1, max = 36) String connectorId,
        @Valid @RequestBody ConnectorPatch connector
    ) {
        throw new RuntimeException("Not implemented");
    }
}
