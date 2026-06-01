package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorFormat;
import com.github.stevecommunity.ocpi.v221.model.locations.types.ConnectorType;
import com.github.stevecommunity.ocpi.v221.model.locations.types.PowerType;
import com.github.stevecommunity.ocpi.v221.model.locations.types.Status;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EvseTests {

    private static final Instant NOW = Instant.parse("2026-01-01T00:00:00Z");

    private final Validator validator = Validation.byDefaultProvider().configure().buildValidatorFactory().getValidator();

    @Test
    void patchAcceptsSparseValidUpdate() {
        EvsePatch patch = new EvsePatch();
        patch.setLast_updated(NOW);

        Set<ConstraintViolation<EvsePatch>> violations = validator.validate(patch);
        assertEquals(0, violations.size());
    }

    @Test
    void patchStillValidatesPatchConstraints() {
        EvsePatch patch = new EvsePatch();
        patch.setLast_updated(NOW);
        patch.setUid("x".repeat(37));

        Set<ConstraintViolation<EvsePatch>> violations = validator.validate(patch);
        assertEquals(1, violations.size());

        Set<String> violatingFieldNames = violatingFieldNames(violations);
        assertEquals(1, violatingFieldNames.size());
        assertTrue(violatingFieldNames.contains("uid"));
    }

    @Test
    void fullObjectAppliesNullCheckConstraints() {
        Evse evse = new Evse();
        evse.setLast_updated(NOW);

        Set<ConstraintViolation<EvsePatch>> violations = validator.validate(evse);
        assertEquals(3, violations.size());

        Set<String> violatingFieldNames = violatingFieldNames(violations);
        assertEquals(3, violatingFieldNames.size());
        assertTrue(violatingFieldNames.containsAll(Set.of(
            "uid",
            "status",
            "connectors"
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

        Evse evse = new Evse();
        evse.setLast_updated(NOW);
        evse.setUid("evse-1");
        evse.setStatus(Status.AVAILABLE);
        evse.setConnectors(List.of(connector));

        Set<ConstraintViolation<EvsePatch>> violations = validator.validate(evse);
        assertEquals(0, violations.size());
    }

    private static Set<String> violatingFieldNames(Set<ConstraintViolation<EvsePatch>> violations) {
        return violations.stream()
            .map(violation -> violation.getPropertyPath().toString())
            .collect(Collectors.toSet());
    }
}
