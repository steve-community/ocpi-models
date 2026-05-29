package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.credentials.Credentials;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = "/ocpi/2.2.1/credentials", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CredentialsApi {

    @GetMapping
    default ResponseEntity<OcpiResponse<Credentials>> getCredentials(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponse<Credentials>> postCredentials(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @Valid @RequestBody Credentials credentials
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponse<Credentials>> putCredentials(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @Valid @RequestBody Credentials credentials
    ) {
        throw new RuntimeException("Not implemented");
    }

    @DeleteMapping
    default ResponseEntity<OcpiResponseVoid> deleteCredentials(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers
    ) {
        throw new RuntimeException("Not implemented");
    }
}
