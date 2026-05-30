package com.github.stevecommunity.ocpi.config;

import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeaders;
import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeadersBase;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import jakarta.validation.Valid;

public class OcpiRequestHeadersArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameterType();
        return OcpiRequestHeadersBase.class.equals(parameterType) || OcpiRequestHeaders.class.equals(parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object headers = OcpiRequestHeaders.class.equals(parameter.getParameterType())
            ? new OcpiRequestHeaders(webRequest)
            : new OcpiRequestHeadersBase(webRequest);

        validateMaybe(parameter, webRequest, binderFactory, headers);
        return headers;
    }

    private static void validateMaybe(MethodParameter parameter, NativeWebRequest webRequest,
                                      WebDataBinderFactory binderFactory, Object headers) throws Exception {
        if (!parameter.hasParameterAnnotation(Valid.class)) {
            return;
        }

        WebDataBinder binder = binderFactory.createBinder(webRequest, headers, parameter.getParameterName());
        binder.validate();

        BindingResult bindingResult = binder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(parameter, bindingResult);
        }
    }
}
