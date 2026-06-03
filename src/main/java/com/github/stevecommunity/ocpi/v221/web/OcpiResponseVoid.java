package com.github.stevecommunity.ocpi.v221.web;

public class OcpiResponseVoid extends OcpiResponse<Void> {

    public OcpiResponseVoid() {
        super(null);
    }

    public static OcpiResponseVoid from() {
        return new OcpiResponseVoid();
    }
}
