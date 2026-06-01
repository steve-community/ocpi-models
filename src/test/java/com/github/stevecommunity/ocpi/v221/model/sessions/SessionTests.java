package com.github.stevecommunity.ocpi.v221.model.sessions;

import com.github.stevecommunity.ocpi.v221.model.cdrs.types.AuthMethod;
import com.github.stevecommunity.ocpi.v221.model.cdrs.types.CdrToken;
import com.github.stevecommunity.ocpi.v221.model.sessions.types.SessionStatus;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SessionTests {

    private static final Instant NOW = Instant.parse("2026-01-01T00:00:00Z");

    private final Validator validator = Validation.byDefaultProvider().configure().buildValidatorFactory().getValidator();

    @Test
    void patchAcceptsSparseValidUpdate() {
        SessionPatch patch = new SessionPatch();
        patch.setLast_updated(NOW);

        Set<ConstraintViolation<SessionPatch>> violations = validator.validate(patch);
        assertEquals(0, violations.size());
    }

    @Test
    void patchStillValidatesPatchConstraints() {
        SessionPatch patch = new SessionPatch();
        patch.setLast_updated(NOW);
        patch.setCountry_code("ABC");

        Set<ConstraintViolation<SessionPatch>> violations = validator.validate(patch);
        assertEquals(1, violations.size());

        Set<String> violatingFieldNames = violatingFieldNames(violations);
        assertEquals(1, violatingFieldNames.size());
        assertTrue(violatingFieldNames.contains("country_code"));
    }

    @Test
    void fullObjectAppliesNullCheckConstraints() {
        Session session = new Session();
        session.setLast_updated(NOW);

        Set<ConstraintViolation<SessionPatch>> violations = validator.validate(session);
        assertEquals(12, violations.size());

        Set<String> violatingFieldNames = violatingFieldNames(violations);
        assertEquals(12, violatingFieldNames.size());
        assertTrue(violatingFieldNames.containsAll(Set.of(
            "country_code",
            "party_id",
            "id",
            "start_date_time",
            "kwh",
            "cdr_token",
            "auth_method",
            "location_id",
            "evse_uid",
            "connector_id",
            "currency",
            "status"
        )));
    }

    @Test
    void fullObjectAcceptsRequiredFields() {
        CdrToken cdrToken = new CdrToken();
        cdrToken.setCountry_code("DE");
        cdrToken.setParty_id("MSP");
        cdrToken.setUid("token-1");
        cdrToken.setType(TokenType.RFID);
        cdrToken.setContract_id("DE-MSP-C123456");

        Session session = new Session();
        session.setLast_updated(NOW);
        session.setCountry_code("DE");
        session.setParty_id("CPO");
        session.setId("session-1");
        session.setStart_date_time(NOW);
        session.setKwh(1.0);
        session.setCdr_token(cdrToken);
        session.setAuth_method(AuthMethod.WHITELIST);
        session.setLocation_id("location-1");
        session.setEvse_uid("evse-1");
        session.setConnector_id("connector-1");
        session.setCurrency("EUR");
        session.setStatus(SessionStatus.ACTIVE);

        Set<ConstraintViolation<SessionPatch>> violations = validator.validate(session);
        assertEquals(0, violations.size());
    }

    private static Set<String> violatingFieldNames(Set<ConstraintViolation<SessionPatch>> violations) {
        return violations.stream()
            .map(violation -> violation.getPropertyPath().toString())
            .collect(Collectors.toSet());
    }
}
