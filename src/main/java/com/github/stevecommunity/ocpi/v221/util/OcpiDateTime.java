package com.github.stevecommunity.ocpi.v221.util;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Applies OCPI DateTime parsing to an {@link java.time.Instant} JSON property. The following examples from OCPI spec
 * illustrate the possibilities for the format:
 * <pre>
 * 2015-06-29T20:39:09Z
 * 2015-06-29T20:39:09
 * 2016-12-29T17:45:09.2Z
 * 2016-12-29T17:45:09.2
 * 2018-01-01T01:08:01.123Z
 * 2018-01-01T01:08:01.123
 * </pre>
 */
@JacksonAnnotationsInside
@JsonDeserialize(using = OcpiDateTimeFormatter.Jackson.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface OcpiDateTime {
}
