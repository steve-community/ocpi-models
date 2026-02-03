package com.github.stevecommunity.ocpi.v221.model.payments.adjustments;

import jakarta.validation.Validator;

/**
 * This interface is used on fields with validation constraints that are only relevant for the payments module.
 * {@link Validator#validate(Object, Class[])} needs to be used with <code>validate(object, PaymentsModule.class)</code>
 * in order for the validator to check this constraint.
 */
public interface PaymentsModule {
}
