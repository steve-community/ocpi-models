package com.github.stevecommunity.ocpi.v221.model.versions.types;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VersionNumber {
    V_2_0("2.0"),
    V_2_1("2.1"),
    V_2_1_1("2.1.1"),
    V_2_2("2.2"),
    V_2_2_1("2.2.1");

    @JsonValue
    private final String value;
}
