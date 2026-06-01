package com.github.stevecommunity.ocpi.v221.model.tokens;

import com.github.stevecommunity.ocpi.v221.model.tokens.types.TokenType;
import com.github.stevecommunity.ocpi.v221.model.tokens.types.WhitelistType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenTests {

    private static final Instant NOW = Instant.parse("2026-01-01T00:00:00Z");

    private final Validator validator = Validation.byDefaultProvider().configure().buildValidatorFactory().getValidator();

    @Test
    void patchAcceptsSparseValidUpdate() {
        TokenPatch patch = new TokenPatch();
        patch.setLast_updated(NOW);

        Set<ConstraintViolation<TokenPatch>> violations = validator.validate(patch);
        assertEquals(0, violations.size());
    }

    @Test
    void patchStillValidatesPatchConstraints() {
        TokenPatch patch = new TokenPatch();
        patch.setLast_updated(NOW);
        patch.setCountry_code("ABC");

        Set<ConstraintViolation<TokenPatch>> violations = validator.validate(patch);
        assertEquals(1, violations.size());

        Set<String> violatingFieldNames = violatingFieldNames(violations);
        assertEquals(1, violatingFieldNames.size());
        assertTrue(violatingFieldNames.contains("country_code"));
    }

    @Test
    void fullObjectAppliesNullCheckConstraints() {
        Token token = new Token();
        token.setLast_updated(NOW);

        Set<ConstraintViolation<TokenPatch>> violations = validator.validate(token);
        assertEquals(8, violations.size());

        Set<String> violatingFieldNames = violatingFieldNames(violations);
        assertEquals(8, violatingFieldNames.size());
        assertTrue(violatingFieldNames.containsAll(Set.of(
            "country_code",
            "party_id",
            "uid",
            "type",
            "contract_id",
            "issuer",
            "valid",
            "whitelist"
        )));
    }

    @Test
    void fullObjectAcceptsRequiredFields() {
        Token token = new Token();
        token.setLast_updated(NOW);
        token.setCountry_code("DE");
        token.setParty_id("MSP");
        token.setUid("token-1");
        token.setType(TokenType.RFID);
        token.setContract_id("DE-MSP-C123456");
        token.setIssuer("MSP");
        token.setValid(true);
        token.setWhitelist(WhitelistType.ALWAYS);

        Set<ConstraintViolation<TokenPatch>> violations = validator.validate(token);
        assertEquals(0, violations.size());
    }

    private static Set<String> violatingFieldNames(Set<ConstraintViolation<TokenPatch>> violations) {
        return violations.stream()
            .map(violation -> violation.getPropertyPath().toString())
            .collect(Collectors.toSet());
    }
}
