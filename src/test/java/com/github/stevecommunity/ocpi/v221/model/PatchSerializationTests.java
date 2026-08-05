package com.github.stevecommunity.ocpi.v221.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stevecommunity.ocpi.v221.model.locations.ConnectorPatch;
import com.github.stevecommunity.ocpi.v221.model.locations.EvsePatch;
import com.github.stevecommunity.ocpi.v221.model.locations.LocationPatch;
import com.github.stevecommunity.ocpi.v221.model.sessions.SessionPatch;
import com.github.stevecommunity.ocpi.v221.model.tokens.TokenPatch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatchSerializationTests {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void connectorPatchOmitsNullFields() {
        ConnectorPatch patch = new ConnectorPatch().setId("connector-1");

        assertSerializesOnly(patch, "id", "connector-1");
    }

    @Test
    void evsePatchOmitsNullFields() {
        EvsePatch patch = new EvsePatch().setUid("evse-1");

        assertSerializesOnly(patch, "uid", "evse-1");
    }

    @Test
    void locationPatchOmitsNullFields() {
        LocationPatch patch = new LocationPatch();
        patch.setId("location-1");

        assertSerializesOnly(patch, "id", "location-1");
    }

    @Test
    void sessionPatchOmitsNullFields() {
        SessionPatch patch = new SessionPatch().setId("session-1");

        assertSerializesOnly(patch, "id", "session-1");
    }

    @Test
    void tokenPatchOmitsNullFields() {
        TokenPatch patch = new TokenPatch();
        patch.setUid("token-1");

        assertSerializesOnly(patch, "uid", "token-1");
    }

    private void assertSerializesOnly(Object patch, String field, String value) {
        JsonNode expected = objectMapper.createObjectNode().put(field, value);

        assertEquals(expected, objectMapper.valueToTree(patch));
    }
}
