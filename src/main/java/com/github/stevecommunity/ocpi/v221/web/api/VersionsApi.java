package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.versions.Version;
import com.github.stevecommunity.ocpi.v221.model.versions.VersionDetails;
import com.github.stevecommunity.ocpi.v221.model.versions.types.VersionNumber;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeadersBase;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import java.util.List;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = "/ocpi/versions", produces = MediaType.APPLICATION_JSON_VALUE)
public interface VersionsApi {

    @GetMapping
    default ResponseEntity<OcpiResponse<List<Version>>> getVersions(
        @Parameter(hidden = true) @Valid OcpiRequestHeadersBase headers
    ) {
        throw new RuntimeException("Not implemented");
    }

    @GetMapping("/{version}")
    default ResponseEntity<OcpiResponse<VersionDetails>> getVersionDetails(
        @Parameter(hidden = true) @Valid OcpiRequestHeadersBase headers,
        @PathVariable("version") VersionNumber version
    ) {
        throw new RuntimeException("Not implemented");
    }
}
