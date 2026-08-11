package com.github.stevecommunity.ocpi.v221.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tools.jackson.databind.json.JsonMapper;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OcpiDateTimeTests {

    private final JsonMapper jsonMapper = new JsonMapper();

    @ParameterizedTest
    @CsvSource({
        "2015-06-29T20:39:09Z, 2015-06-29T20:39:09Z",
        "2015-06-29T20:39:09,  2015-06-29T20:39:09Z",
        "2016-12-29T17:45:09.2Z, 2016-12-29T17:45:09.2Z",
        "2016-12-29T17:45:09.2,  2016-12-29T17:45:09.2Z",
        "2018-01-01T01:08:01.123Z, 2018-01-01T01:08:01.123Z",
        "2018-01-01T01:08:01.123,  2018-01-01T01:08:01.123Z",
        "2026-08-11T07:47:01.316831, 2026-08-11T07:47:01.316831Z"
    })
    void parsesAnnotatedInstantAsUtc(String value, String expected) throws Exception {
        TestPayload payload = jsonMapper.readValue(
            "{\"timestamp\":\"%s\"}".formatted(value),
            TestPayload.class
        );

        assertEquals(Instant.parse(expected), payload.timestamp);
    }

    @Test
    void serializesAsCanonicalUtcString() throws Exception {
        TestPayload payload = new TestPayload();
        payload.timestamp = Instant.parse("2026-08-11T07:47:01.316831Z");

        assertEquals(
            "{\"timestamp\":\"2026-08-11T07:47:01.316831Z\"}",
            jsonMapper.writeValueAsString(payload)
        );
    }

    @Test
    void annotationDoesNotChangeThePropertyType() throws Exception {
        TestPayload payload = jsonMapper.readValue(
            "{\"timestamp\":\"2026-08-11T07:47:01.316831\"}",
            TestPayload.class
        );

        assertEquals(Instant.class, payload.timestamp.getClass());
    }

    private static final class TestPayload {
        @OcpiDateTime
        public Instant timestamp;
    }
}
