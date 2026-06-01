package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.commands.CancelReservation;
import com.github.stevecommunity.ocpi.v221.model.commands.CommandResponse;
import com.github.stevecommunity.ocpi.v221.model.commands.CommandResult;
import com.github.stevecommunity.ocpi.v221.model.commands.ReserveNow;
import com.github.stevecommunity.ocpi.v221.model.commands.StartSession;
import com.github.stevecommunity.ocpi.v221.model.commands.StopSession;
import com.github.stevecommunity.ocpi.v221.model.commands.UnlockConnector;
import com.github.stevecommunity.ocpi.v221.model.commands.types.CommandType;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.api.CommandsReceiverApi;
import com.github.stevecommunity.ocpi.v221.web.api.CommandsSenderApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class CommandsClient extends AbstractClient implements CommandsReceiverApi, CommandsSenderApi {

    private static final ParameterizedTypeReference<OcpiResponse<CommandResponse>> COMMAND_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    public CommandsClient(RestTemplate restTemplate, String commandsEndpointRoot, String authorizationToken) {
        super(commandsEndpointRoot, authorizationToken);
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public ResponseEntity<OcpiResponse<CommandResponse>> cancelReservation(OcpiRequestHeaders headers, CancelReservation command) {
        return postCommand(headers, "/CANCEL_RESERVATION", command);
    }

    @Override
    public ResponseEntity<OcpiResponse<CommandResponse>> reserveNow(OcpiRequestHeaders headers, ReserveNow command) {
        return postCommand(headers, "/RESERVE_NOW", command);
    }

    @Override
    public ResponseEntity<OcpiResponse<CommandResponse>> startSession(OcpiRequestHeaders headers, StartSession command) {
        return postCommand(headers, "/START_SESSION", command);
    }

    @Override
    public ResponseEntity<OcpiResponse<CommandResponse>> stopSession(OcpiRequestHeaders headers, StopSession command) {
        return postCommand(headers, "/STOP_SESSION", command);
    }

    @Override
    public ResponseEntity<OcpiResponse<CommandResponse>> unlockConnector(OcpiRequestHeaders headers, UnlockConnector command) {
        return postCommand(headers, "/UNLOCK_CONNECTOR", command);
    }

    @Override
    public ResponseEntity<OcpiResponseVoid> postCommandResult(OcpiRequestHeaders headers, CommandType command, CommandResult result) {
        return restTemplate.exchange(
            endpoint("/{command}"),
            HttpMethod.POST,
            new HttpEntity<>(result, httpHeaders(headers)),
            OcpiResponseVoid.class,
            command
        );
    }

    private ResponseEntity<OcpiResponse<CommandResponse>> postCommand(OcpiRequestHeaders headers, String path, Object body) {
        return restTemplate.exchange(
            endpoint(path),
            HttpMethod.POST,
            new HttpEntity<>(body, httpHeaders(headers)),
            COMMAND_RESPONSE
        );
    }
}
