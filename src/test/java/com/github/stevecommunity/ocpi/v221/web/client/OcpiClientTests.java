package com.github.stevecommunity.ocpi.v221.web.client;

import com.github.stevecommunity.ocpi.v221.web.OcpiResponseVoid;
import com.github.stevecommunity.ocpi.v221.web.OcpiResponse;
import com.github.stevecommunity.ocpi.v221.web.StatusCode;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OcpiClientTests {

    @Test
    void acceptsSuccessfulVoidResponse() {
        var restTemplate = new StubRestTemplate(ResponseEntity.ok(
            OcpiResponseVoid.from().setStatus_code(StatusCode.SUCCESS)
        ));
        var client = new OcpiClient(restTemplate, new HttpHeaders());

        assertDoesNotThrow(() -> client.put("https://example.test/tariffs/1", new Object()));
    }

    @Test
    void rejectsOcpiErrorInHttpSuccessfulVoidResponse() {
        var restTemplate = new StubRestTemplate(ResponseEntity.ok(
            OcpiResponseVoid.error(StatusCode.CLIENT_ERROR, "Tariff rejected")
        ));
        var client = new OcpiClient(restTemplate, new HttpHeaders());

        OcpiResponseException exception = assertThrows(
            OcpiResponseException.class,
            () -> client.put("https://example.test/tariffs/1", new Object())
        );

        assertEquals(StatusCode.CLIENT_ERROR, exception.getStatusCode());
        assertEquals("Tariff rejected", exception.getStatusMessage());
    }

    @Test
    void rejectsMissingVoidResponseBody() {
        var client = new OcpiClient(new StubRestTemplate(ResponseEntity.ok().build()), new HttpHeaders());

        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> client.delete("https://example.test/tariffs/1")
        );

        assertEquals("OCPI response body is missing", exception.getMessage());
    }

    @Test
    void rejectsOcpiErrorBeforeUnwrappingTypedResponse() {
        var restTemplate = new StubRestTemplate(ResponseEntity.ok(
            OcpiResponse.<String>error(StatusCode.SERVER_ERROR, "Remote failure")
        ));
        var client = new OcpiClient(restTemplate, new HttpHeaders());

        OcpiResponseException exception = assertThrows(
            OcpiResponseException.class,
            () -> client.get(
                "https://example.test/resource",
                new ParameterizedTypeReference<OcpiResponse<String>>() { }
            )
        );

        assertEquals(StatusCode.SERVER_ERROR, exception.getStatusCode());
    }

    private static final class StubRestTemplate extends RestTemplate {
        private final ResponseEntity<?> response;

        private StubRestTemplate(ResponseEntity<?> response) {
            this.response = response;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
                                               Class<T> responseType, Object... uriVariables) {
            return (ResponseEntity<T>) response;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
                                               ParameterizedTypeReference<T> responseType,
                                               Object... uriVariables) {
            return (ResponseEntity<T>) response;
        }
    }
}
