package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.commands.CommandResult;
import com.github.stevecommunity.ocpi.v221.model.commands.types.CommandType;
import com.github.stevecommunity.ocpi.v221.web.OcpiApi;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = OcpiApi.COMMANDS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public interface CommandsSenderApi extends OcpiApi.Sender.Commands {

    @PostMapping(value = "/{command}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> postCommandResult(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @PathVariable("command") CommandType command,
        @Valid @RequestBody CommandResult result
    ) {
        throw new RuntimeException("Not implemented");
    }
}
