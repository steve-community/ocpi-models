package com.github.stevecommunity.ocpi.v221.web;

import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OcpiResponseDeserializationTests {

    private final JsonMapper jsonMapper = new JsonMapper();

    @Test
    void deserializesIncomingResponse() throws Exception {
        String json = """
            {
              "data": "accepted",
              "status_code": 1000,
              "status_message": "OK",
              "timestamp": "2026-08-10T15:00:00Z"
            }
            """;

        OcpiResponse<String> response = jsonMapper.readValue(
            json,
            new TypeReference<OcpiResponse<String>>() { }
        );

        assertEquals("accepted", response.getData());
        assertEquals(StatusCode.SUCCESS, response.getStatus_code());
        assertEquals("OK", response.getStatus_message());
        assertEquals(Instant.parse("2026-08-10T15:00:00Z"), response.getTimestamp());
    }

    @Test
    void deserializesIncomingVoidResponse() throws Exception {
        String json = """
            {
              "status_code": 1000,
              "status_message": "OK",
              "timestamp": "2026-08-10T15:00:00Z"
            }
            """;

        OcpiResponseVoid response = jsonMapper.readValue(json, OcpiResponseVoid.class);

        assertEquals(StatusCode.SUCCESS, response.getStatus_code());
        assertEquals("OK", response.getStatus_message());
        assertEquals(Instant.parse("2026-08-10T15:00:00Z"), response.getTimestamp());
    }
}
