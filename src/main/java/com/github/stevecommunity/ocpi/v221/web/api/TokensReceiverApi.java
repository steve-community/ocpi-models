package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.tokens.Token;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@RequestMapping(value = "/ocpi/2.2.1/tokens", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TokensReceiverApi {

    @GetMapping("/{country_code}/{party_id}/{token_uid}")
    default ResponseEntity<OcpiResponse<Token>> getToken(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("token_uid") @Size(min = 1, max = 36) String tokenUid,
        @RequestParam(required = false) TokenType type
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PutMapping(value = "/{country_code}/{party_id}/{token_uid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> putToken(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("token_uid") @Size(min = 1, max = 36) String tokenUid,
        @RequestParam(required = false) TokenType type,
        @Valid @RequestBody Token token
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PatchMapping(value = "/{country_code}/{party_id}/{token_uid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> patchToken(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("country_code") @Size(min = 2, max = 2) String countryCode,
        @PathVariable("party_id") @Size(min = 3, max = 3) String partyId,
        @PathVariable("token_uid") @Size(min = 1, max = 36) String tokenUid,
        @RequestParam(required = false) TokenType type,
        @RequestBody Token token
    ) {
        throw new RuntimeException("Not implemented");
    }
}
