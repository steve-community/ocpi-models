package com.github.stevecommunity.ocpi.config;

import com.github.stevecommunity.ocpi.v221.web.OcpiRequestHeadersAdvice;
import com.github.stevecommunity.ocpi.v221.web.VersionNumberConverter;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class OcpiAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public VersionNumberConverter versionNumberConverter() {
        return new VersionNumberConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    public OcpiRequestHeadersAdvice ocpiRequestHeadersAdvice() {
        return new OcpiRequestHeadersAdvice();
    }

    @Bean
    @ConditionalOnClass(OpenApiCustomizer.class)
    @ConditionalOnMissingBean(name = "ocpiHeadersCustomizer")
    public OpenApiCustomizer ocpiHeadersCustomizer() {
        return openApi -> {
            if (openApi.getPaths() == null) {
                return;
            }

            for (var pathEntry : openApi.getPaths().entrySet()) {
                String path = pathEntry.getKey();
                if (path.startsWith("/ocpi")) {
                    for (Operation operation : pathEntry.getValue().readOperations()) {
                        addOcpiHeaders(operation);
                    }
                }
            }
        };
    }

    private static void addOcpiHeaders(Operation operation) {
        operation.addParametersItem(header("X-Request-ID", true));
        operation.addParametersItem(header("X-Correlation-ID", true));
        operation.addParametersItem(header("OCPI-from-country-code", false));
        operation.addParametersItem(header("OCPI-from-party-id", false));
        operation.addParametersItem(header("OCPI-to-country-code", false));
        operation.addParametersItem(header("OCPI-to-party-id", false));
    }

    private static Parameter header(String name, boolean required) {
        return new Parameter()
            .in("header")
            .name(name)
            .required(required)
            .schema(new StringSchema());
    }
}
