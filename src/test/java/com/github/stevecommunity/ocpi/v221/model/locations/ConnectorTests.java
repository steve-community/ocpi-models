package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorFormat;
import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorType;
import com.github.stevecommunity.ocpi.v221.model.locations.types.PowerType;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConnectorTests {

    private static final Instant NOW = Instant.parse("2026-01-01T00:00:00Z");

    private final Validator validator = Validation.byDefaultProvider().configure().buildValidatorFactory().getValidator();

    @Test
    void patchAcceptsSparseValidUpdate() {
        ConnectorPatch patch = new ConnectorPatch();
        patch.setLast_updated(NOW);

        Set<ConstraintViolation<ConnectorPatch>> violations = validator.validate(patch);
        assertEquals(0, violations.size());
    }

    @Test
    void patchStillValidatesPatchConstraints() {
        ConnectorPatch patch = new ConnectorPatch();
        patch.setLast_updated(NOW);
        patch.setId("x".repeat(37));

        Set<ConstraintViolation<ConnectorPatch>> violations = validator.validate(patch);
        assertEquals(1, violations.size());

        Set<String> violatingFieldNames = violatingFieldNames(violations);
        assertEquals(1, violatingFieldNames.size());
        assertTrue(violatingFieldNames.contains("id"));
    }

    @Test
    void fullObjectAppliesNullCheckConstraints() {
        Connector connector = new Connector();
        connector.setLast_updated(NOW);

        Set<ConstraintViolation<ConnectorPatch>> violations = validator.validate(connector);
        assertEquals(6, violations.size());

        Set<String> violatingFieldNames = violatingFieldNames(violations);
        assertEquals(6, violatingFieldNames.size());
        assertTrue(violatingFieldNames.containsAll(Set.of(
            "id",
            "standard",
            "format",
            "power_type",
            "max_voltage",
            "max_amperage"
        )));
    }

    @Test
    void fullObjectAcceptsRequiredFields() {
        Connector connector = new Connector();
        connector.setLast_updated(NOW);
        connector.setId("connector-1");
        connector.setStandard(ConnectorType.IEC_62196_T2);
        connector.setFormat(ConnectorFormat.SOCKET);
        connector.setPower_type(PowerType.AC_3_PHASE);
        connector.setMax_voltage(400);
        connector.setMax_amperage(32);

        Set<ConstraintViolation<ConnectorPatch>> violations = validator.validate(connector);
        assertEquals(0, violations.size());
    }

    private static Set<String> violatingFieldNames(Set<ConstraintViolation<ConnectorPatch>> violations) {
        return violations.stream()
            .map(violation -> violation.getPropertyPath().toString())
            .collect(Collectors.toSet());
    }
}
