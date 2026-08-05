package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.web.OcpiResponseEnvelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Objects;

@Slf4j
public class OcpiClientWithValidation extends OcpiClient {

    private final Validator validator;
    private final ValidationStrictnessMode readingMode;
    private final ValidationStrictnessMode writingMode;

    OcpiClientWithValidation(RestTemplate restTemplate,
                             HttpHeaders headers,
                             Validator validator,
                             ValidationStrictnessMode readingMode,
                             ValidationStrictnessMode writingMode) {
        super(restTemplate, headers);
        this.validator = Objects.requireNonNull(validator, "validator must not be null");
        this.readingMode = readingMode;
        this.writingMode = writingMode;
    }

    @Override
    protected void checkBeforeSend(Object payload) {
        validate(payload, writingMode, "outgoing");
    }

    @Override
    protected void checkAfterReceive(OcpiResponseEnvelope response) {
        validate(response, readingMode, "incoming");
    }

    private void validate(Object payload, ValidationStrictnessMode mode, String direction) {
        if (mode == null || payload == null) {
            return;
        }

        var violations = validator.validate(payload);
        if (violations.isEmpty()) {
            return;
        }

        String message = "Invalid " + direction + " OCPI payload";

        switch (mode) {
            case LogWarning -> log.warn(message, new ConstraintViolationException(violations));
            case ThrowError -> throw new ConstraintViolationException(message, violations);
        }
    }
}
