package com.github.stevecommunity.ocpi.v221.model.locations;

import com.github.stevecommunity.ocpi.v221.model.locations.types.GeoLocation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocationTests {

    private static final Instant NOW = Instant.parse("2026-01-01T00:00:00Z");

    private final Validator validator = Validation.byDefaultProvider().configure().buildValidatorFactory().getValidator();

    @Test
    void patchAcceptsSparseValidUpdate() {
        LocationPatch patch = new LocationPatch();
        patch.setLast_updated(NOW);

        Set<ConstraintViolation<LocationPatch>> violations = validator.validate(patch);
        assertEquals(0, violations.size());
    }

    @Test
    void patchStillValidatesPatchConstraints() {
        LocationPatch patch = new LocationPatch();
        patch.setLast_updated(NOW);
        patch.setCountry_code("ABC");

        Set<ConstraintViolation<LocationPatch>> violations = validator.validate(patch);
        assertEquals(1, violations.size());

        Set<String> violatingFieldNames = violatingFieldNames(violations);
        assertEquals(1, violatingFieldNames.size());
        assertTrue(violatingFieldNames.contains("country_code"));
    }

    @Test
    void fullObjectAppliesNullCheckConstraints() {
        Location location = new Location();
        location.setLast_updated(NOW);

        Set<ConstraintViolation<LocationPatch>> violations = validator.validate(location);
        assertEquals(9, violations.size());

        Set<String> violatingFieldNames = violatingFieldNames(violations);
        assertEquals(9, violatingFieldNames.size());
        assertTrue(violatingFieldNames.containsAll(Set.of(
            "id",
            "address",
            "city",
            "country",
            "coordinates",
            "country_code",
            "party_id",
            "publish",
            "time_zone"
        )));
    }

    @Test
    void fullObjectAcceptsRequiredFields() {
        GeoLocation coordinates = new GeoLocation();
        coordinates.setLatitude("52.52000");
        coordinates.setLongitude("13.40500");

        Location location = new Location();
        location.setLast_updated(NOW);
        location.setId("location-1");
        location.setAddress("Main Street 1");
        location.setCity("Berlin");
        location.setCountry("DEU");
        location.setCoordinates(coordinates);
        location.setCountry_code("DE");
        location.setParty_id("CPO");
        location.setPublish(true);
        location.setTime_zone("Europe/Berlin");

        Set<ConstraintViolation<LocationPatch>> violations = validator.validate(location);
        assertEquals(0, violations.size());
    }

    private static Set<String> violatingFieldNames(Set<ConstraintViolation<LocationPatch>> violations) {
        return violations.stream()
            .map(violation -> violation.getPropertyPath().toString())
            .collect(Collectors.toSet());
    }
}
