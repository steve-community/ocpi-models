package com.github.stevecommunity.ocpi.v221.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OcpiRequestParametersTests {

    @Test
    void usesDefaultLimitWhenUnset() {
        OcpiRequestParameters params = new OcpiRequestParameters();

        assertEquals(OcpiRequestParameters.DEFAULT_LIMIT, params.getLimit());
    }

    @Test
    void usesDefaultLimitWhenLimitIsNull() {
        OcpiRequestParameters params = new OcpiRequestParameters();
        params.setLimit(null);

        assertEquals(OcpiRequestParameters.DEFAULT_LIMIT, params.getLimit());
    }

    @Test
    void capsLimitAtMaxLimit() {
        OcpiRequestParameters params = new OcpiRequestParameters();
        params.setLimit(OcpiRequestParameters.MAX_LIMIT + 10000);

        assertEquals(OcpiRequestParameters.MAX_LIMIT, params.getLimit());
    }
}
