package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.cdrs.Cdr;
import com.github.stevecommunity.ocpi.v221.web.OcpiApi;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = OcpiApi.CDRS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public interface CdrsReceiverApi extends OcpiApi.Cdrs.Receiver {

    @GetMapping("/{cdr_id}")
    default ResponseEntity<OcpiResponse<Cdr>> getCdr(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @PathVariable("cdr_id") @Size(min = 1, max = 39) String cdrId
    ) {
        throw new RuntimeException("Not implemented");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<OcpiResponseVoid> postCdr(
        @Parameter(hidden = true) @Valid OcpiRequestHeaders headers,
        @Valid @RequestBody Cdr cdr
    ) {
        throw new RuntimeException("Not implemented");
    }
}
