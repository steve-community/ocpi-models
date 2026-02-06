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
    Instant date_from;
    Instant date_to;
    @Min(value = 0) Integer offset = 0;
    @Min(value = 0) Integer limit;
}
