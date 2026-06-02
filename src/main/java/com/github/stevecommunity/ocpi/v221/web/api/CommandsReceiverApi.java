package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.commands.CancelReservation;
import com.github.stevecommunity.ocpi.v221.model.commands.CommandResponse;
import com.github.stevecommunity.ocpi.v221.model.commands.ReserveNow;
import com.github.stevecommunity.ocpi.v221.model.commands.StartSession;
import com.github.stevecommunity.ocpi.v221.model.commands.StopSession;
import com.github.stevecommunity.ocpi.v221.model.commands.UnlockConnector;
import com.github.stevecommunity.ocpi.v221.web.OcpiApi;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = OcpiApi.COMMANDS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public interface CommandsReceiverApi extends OcpiApi.Receiver.Commands {

    @PostMapping(value = "/CANCEL_RESERVATION", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponse<CommandResponse>> cancelReservation(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @Valid @RequestBody CancelReservation command
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PostMapping(value = "/RESERVE_NOW", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponse<CommandResponse>> reserveNow(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @Valid @RequestBody ReserveNow command
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PostMapping(value = "/START_SESSION", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponse<CommandResponse>> startSession(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @Valid @RequestBody StartSession command
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PostMapping(value = "/STOP_SESSION", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponse<CommandResponse>> stopSession(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @Valid @RequestBody StopSession command
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PostMapping(value = "/UNLOCK_CONNECTOR", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponse<CommandResponse>> unlockConnector(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @Valid @RequestBody UnlockConnector command
    ) {
        throw new RuntimeException("Not implemented");
    }
}
