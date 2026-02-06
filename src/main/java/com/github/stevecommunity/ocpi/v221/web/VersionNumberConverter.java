package com.github.stevecommunity.ocpi.v221.web;

import com.github.stevecommunity.ocpi.v221.model.versions.types.VersionNumber;
import org.springframework.core.convert.converter.Converter;

public class VersionNumberConverter implements Converter<String, VersionNumber> {
    @Override
    public VersionNumber convert(String source) {
        return VersionNumber.fromValue(source);
    }
}
