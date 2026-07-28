package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.model.commands.CommandResult;
import com.github.stevecommunity.ocpi.v221.model.versions.Version;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.StatusCode;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OcpiClientWithValidationTests {

    private final Validator validator = Validation
        .byDefaultProvider()
        .configure()
        .buildValidatorFactory()
        .getValidator();

    @Test
    void rejectsInvalidOutgoingPayloadBeforeSendingRequest() {
        var restTemplate = new StubRestTemplate(successfulVoidResponse());
        var client = client(restTemplate, null, ValidationStrictnessMode.ThrowError);

        ConstraintViolationException exception = assertThrows(
            ConstraintViolationException.class,
            () -> client.sendResult("https://example.test/commands/result", new CommandResult())
        );

        assertTrue(exception.getMessage().contains("Invalid outgoing OCPI payload"));
        assertTrue(exception.getConstraintViolations().stream()
            .anyMatch(violation -> violation.getPropertyPath().toString().equals("result")));
        assertEquals(0, restTemplate.getExchangeCount());
    }

    @Test
    void rejectsInvalidObjectInIncomingCollection() {
        var response = ResponseEntity.ok(
            OcpiResponse.from(List.of(new Version())).setStatus_code(StatusCode.SUCCESS)
        );
        var restTemplate = new StubRestTemplate(response);
        var client = client(restTemplate, ValidationStrictnessMode.ThrowError, null);

        ConstraintViolationException exception = assertThrows(
            ConstraintViolationException.class,
            () -> client.getVersions("https://example.test/versions")
        );

        assertTrue(exception.getMessage().contains("Invalid incoming OCPI payload"));
        assertTrue(exception.getConstraintViolations().stream()
            .anyMatch(violation -> violation.getPropertyPath().toString().equals("data[0].version")));
        assertEquals(1, restTemplate.getExchangeCount());
    }

    @Test
    void logsInvalidPayloadAndContinuesInWarningMode() {
        var restTemplate = new StubRestTemplate(successfulVoidResponse());
        var client = client(restTemplate, null, ValidationStrictnessMode.LogWarning);

        assertDoesNotThrow(
            () -> client.sendResult("https://example.test/commands/result", new CommandResult())
        );

        assertEquals(1, restTemplate.getExchangeCount());
    }

    private OcpiClient client(StubRestTemplate restTemplate,
                              ValidationStrictnessMode readingMode,
                              ValidationStrictnessMode writingMode) {
        return new OcpiClientWithValidation(
            restTemplate,
            new HttpHeaders(),
            validator,
            readingMode,
            writingMode
        );
    }

    private static ResponseEntity<OcpiResponseVoid> successfulVoidResponse() {
        return ResponseEntity.ok(OcpiResponseVoid.from().setStatus_code(StatusCode.SUCCESS));
    }

    private static final class StubRestTemplate extends RestTemplate {
        private final ResponseEntity<?> response;
        private int exchangeCount;

        private StubRestTemplate(ResponseEntity<?> response) {
            this.response = response;
        }

        private int getExchangeCount() {
            return exchangeCount;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
                                               Class<T> responseType, Object... uriVariables) {
            exchangeCount++;
            return (ResponseEntity<T>) response;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
                                               ParameterizedTypeReference<T> responseType,
                                               Object... uriVariables) {
            exchangeCount++;
            return (ResponseEntity<T>) response;
        }
    }
}
