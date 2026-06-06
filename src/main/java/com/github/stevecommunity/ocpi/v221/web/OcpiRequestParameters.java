package com.github.stevecommunity.ocpi.v221.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.Min;
import java.time.Instant;

@Getter
@Setter
@ToString
public class OcpiRequestParameters {

    public static final int DEFAULT_LIMIT = 50;
    public static final int MAX_LIMIT = 200;

    Instant date_from;
    Instant date_to;
    @Min(value = 0) Integer offset = 0;
    @Min(value = 0) Integer limit = DEFAULT_LIMIT;

    public Integer getLimit() {
        if (limit == null) {
            return DEFAULT_LIMIT;
        }

        return Math.min(limit, MAX_LIMIT);
    }
}
