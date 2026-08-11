package com.github.stevecommunity.ocpi.v221.web;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OcpiResponseDeserializationTests {

    private final JsonMapper jsonMapper = new JsonMapper();

    @ParameterizedTest
    @ValueSource(strings = {
        "2015-06-29T20:39:09Z",
        "2015-06-29T20:39:09",
        "2016-12-29T17:45:09.2Z",
        "2016-12-29T17:45:09.2",
        "2018-01-01T01:08:01.123Z",
        "2018-01-01T01:08:01.123"
    })
    void deserializesIncomingResponse(String timestamp) throws Exception {
        String json = """
            {
              "data": "accepted",
              "status_code": 1000,
              "status_message": "OK",
              "timestamp": "%s"
            }
            """.formatted(timestamp);

        OcpiResponse<String> response = jsonMapper.readValue(
            json,
            new TypeReference<OcpiResponse<String>>() { }
        );

        assertEquals("accepted", response.getData());
        assertEquals(StatusCode.SUCCESS, response.getStatus_code());
        assertEquals("OK", response.getStatus_message());
        assertEquals(expectedInstant(timestamp), response.getTimestamp());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "2015-06-29T20:39:09Z",
        "2015-06-29T20:39:09",
        "2016-12-29T17:45:09.2Z",
        "2016-12-29T17:45:09.2",
        "2018-01-01T01:08:01.123Z",
        "2018-01-01T01:08:01.123"
    })
    void deserializesIncomingVoidResponse(String timestamp) throws Exception {
        String json = """
            {
              "status_code": 1000,
              "status_message": "OK",
              "timestamp": "%s"
            }
            """.formatted(timestamp);

        OcpiResponseVoid response = jsonMapper.readValue(json, OcpiResponseVoid.class);

        assertEquals(StatusCode.SUCCESS, response.getStatus_code());
        assertEquals("OK", response.getStatus_message());
        assertEquals(expectedInstant(timestamp), response.getTimestamp());
    }

    private static Instant expectedInstant(String timestamp) {
        return Instant.parse(timestamp.endsWith("Z") ? timestamp : timestamp + "Z");
    }
}
