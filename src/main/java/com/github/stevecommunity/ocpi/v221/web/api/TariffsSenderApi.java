package com.github.stevecommunity.ocpi.v221.web.api;

import com.github.stevecommunity.ocpi.v221.model.tariffs.Tariff;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestParameters;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import java.util.List;

@RequestMapping(value = "/ocpi/2.2.1/tariffs", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TariffsSenderApi {

    @GetMapping
    default ResponseEntity<OcpiResponse<List<Tariff>>> getTariffs(
        @Parameter(hidden = true) @Valid @ModelAttribute OcpiRequestHeaders headers,
        @Valid @ParameterObject OcpiRequestParameters params
    ) {
        throw new RuntimeException("Not implemented");
    }
}
