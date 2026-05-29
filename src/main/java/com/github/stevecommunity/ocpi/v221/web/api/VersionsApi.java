package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.versions.Version;
import com.github.stevecommunity.ocpi.v221.model.versions.VersionDetails;
import com.github.stevecommunity.ocpi.v221.model.versions.types.VersionNumber;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import java.util.List;

import static com.github.stevecommunity.ocpi.config.OcpiAutoConfiguration.OCPI_AUTH_SCHEME;

@SecurityRequirement(name = OCPI_AUTH_SCHEME)
@RequestMapping(value = "/ocpi", produces = MediaType.APPLICATION_JSON_VALUE)
public interface VersionsApi {
    @GetMapping("/versions")
    default ResponseEntity<OcpiResponse<List<Version>>> getVersions(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers
    ) {
        throw new RuntimeException("Not implemented");
    }

    @GetMapping("/versions/{version}")
    default ResponseEntity<OcpiResponse<VersionDetails>> getVersionDetails(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @PathVariable("version") VersionNumber version
    ) {
        throw new RuntimeException("Not implemented");
    }
}
