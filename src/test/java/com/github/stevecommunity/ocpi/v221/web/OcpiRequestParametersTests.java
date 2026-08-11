package com.github.stevecommunity.ocpi.v221.web;

import com.github.stevecommunity.ocpi.v221.util.OcpiDateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.DataBinder;

import java.time.Instant;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OcpiRequestParametersTests {

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
    void bindsOcpiDateTimeQueryParameters(String value, String expected) {
        var conversionService = new DefaultFormattingConversionService();
        conversionService.addFormatterForFieldAnnotation(new OcpiDateTimeFormatter.SpringMvc());

        var params = new OcpiRequestParameters();
        var binder = new DataBinder(params);
        binder.setConversionService(conversionService);
        binder.bind(new MutablePropertyValues(Map.of(
            "date_from", value,
            "date_to", value
        )));

        assertFalse(binder.getBindingResult().hasErrors());
        assertEquals(Instant.parse(expected), params.getDate_from());
        assertEquals(Instant.parse(expected), params.getDate_to());
    }

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
