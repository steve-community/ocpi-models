package com.github.stevecommunity.ocpi.config;

import com.github.stevecommunity.ocpi.v221.web.VersionNumberConverter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class OcpiAutoConfiguration {

    public static final String OCPI_AUTH_SCHEME = "ocpiAuth";

    private static final Set<String> NO_ROUTING_PATHS = Set.of(
        "/ocpi/2.2.1/credentials",
        "/ocpi/2.2.1/hubclientinfo",
        "/ocpi/versions"
    );

    @Bean
    @ConditionalOnMissingBean
    public VersionNumberConverter versionNumberConverter() {
        return new VersionNumberConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    public OcpiRequestHeadersArgumentResolver ocpiRequestHeadersArgumentResolver() {
        return new OcpiRequestHeadersArgumentResolver();
    }

    @Bean
    @ConditionalOnMissingBean(name = "ocpiWebMvcConfigurer")
    public WebMvcConfigurer ocpiWebMvcConfigurer(OcpiRequestHeadersArgumentResolver resolver) {
        return new WebMvcConfigurer() {
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(resolver);
            }
        };
    }

    @Bean
    @ConditionalOnClass(OpenApiCustomizer.class)
    @ConditionalOnMissingBean(name = "ocpiCustomizer")
    public OpenApiCustomizer ocpiCustomizer() {
        return openApi -> {
            addOcpiAuthScheme(openApi);

            if (openApi.getPaths() == null) {
                return;
            }

            for (var pathEntry : openApi.getPaths().entrySet()) {
                String path = pathEntry.getKey();
                if (path.startsWith("/ocpi")) {
                    boolean isNoRouting = NO_ROUTING_PATHS.stream().anyMatch(path::startsWith);

                    Consumer<Operation> action = isNoRouting
                        ? OcpiAutoConfiguration::addOcpiHeadersNoRouting
                        : OcpiAutoConfiguration::addOcpiHeadersWithRouting;

                    for (Operation operation : pathEntry.getValue().readOperations()) {
                        action.accept(operation);
                    }
                }
            }
        };
    }

    private static void addOcpiAuthScheme(OpenAPI openApi) {
        if (openApi.getComponents() == null) {
            openApi.setComponents(new Components());
        }

        var securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER)
            .name("Authorization")
            .description("OCPI credentials token. Header value format: `Token <base64-credentials-token>`.");

        openApi.getComponents().addSecuritySchemes(OCPI_AUTH_SCHEME, securityScheme);
    }

    private static void addOcpiHeadersNoRouting(Operation operation) {
        operation.addParametersItem(header("X-Request-ID", true));
        operation.addParametersItem(header("X-Correlation-ID", true));
    }

    private static void addOcpiHeadersWithRouting(Operation operation) {
        addOcpiHeadersNoRouting(operation);

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
