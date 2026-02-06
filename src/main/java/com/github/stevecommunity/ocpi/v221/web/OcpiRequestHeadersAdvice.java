package com.github.stevecommunity.ocpi.v221.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
public class OcpiRequestHeadersAdvice {

    @ModelAttribute
    public OcpiRequestHeaders resolveHeaders(NativeWebRequest request) {
        return OcpiRequestHeaders.fromRequestHeaders(request);
    }
}
