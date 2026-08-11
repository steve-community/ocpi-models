package com.github.stevecommunity.ocpi.v221.util;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import tools.jackson.databind.ext.javatime.deser.InstantDeserializer;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Set;

/**
 * The default Instant deserializer is too strict. We need to be able to support the following formats:
 * <pre>
 * 2015-06-29T20:39:09Z
 * 2015-06-29T20:39:09
 * 2016-12-29T17:45:09.2Z
 * 2016-12-29T17:45:09.2
 * 2018-01-01T01:08:01.123Z
 * 2018-01-01T01:08:01.123
 * </pre>
 */
public final class OcpiDateTimeFormatter {

    static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
        .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        .optionalStart()
        .appendLiteral('Z')
        .optionalEnd()
        .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
        .toFormatter(Locale.ROOT)
        .withResolverStyle(ResolverStyle.STRICT);

    public static final class Jackson extends InstantDeserializer<Instant> {

        public Jackson() {
            super(InstantDeserializer.INSTANT, FORMATTER);
        }
    }

    public static final class SpringMvc implements AnnotationFormatterFactory<OcpiDateTime> {
        @Override
        public Set<Class<?>> getFieldTypes() {
            return Set.of(Instant.class);
        }

        @Override
        public Printer<?> getPrinter(OcpiDateTime annotation, Class<?> fieldType) {
            return (Printer<Instant>) (value, locale) -> value.toString();
        }

        @Override
        public Parser<?> getParser(OcpiDateTime annotation, Class<?> fieldType) {
            return (Parser<Instant>) (value, locale) -> Instant.from(FORMATTER.parse(value));
        }
    }
}
