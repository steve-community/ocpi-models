package com.github.stevecommunity.ocpi.v221.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class OcpiRequestHeadersBase {
    @NotNull String xRequestId;
    @NotNull String xCorrelationId;
}
