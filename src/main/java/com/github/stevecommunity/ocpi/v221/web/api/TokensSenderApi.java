package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.tokens.AuthorizationInfo;
import com.github.stevecommunity.ocpi.v221.model.tokens.Token;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.LocationReferences;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.List;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = "/ocpi/2.2.1/tokens", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TokensSenderApi {

    @GetMapping
    default ResponseEntity<OcpiResponse<List<Token>>> getTokens(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @Valid @ParameterObject OcpiRequestParameters params
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PostMapping(value = "/{token_uid}/authorize", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponse<AuthorizationInfo>> authorizeToken(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("token_uid") @Size(min = 1, max = 36) String tokenUid,
        @RequestParam(required = false) TokenType type,
        @RequestBody(required = false) LocationReferences locationReferences
    ) {
        throw new RuntimeException("Not implemented");
    }
}
