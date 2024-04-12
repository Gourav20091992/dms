package com.ncba.miniapp.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "My API", version = "1.0", description = "API for My Application"))
public class OpenApiConfig {

    @Bean
    public OpenAPI miniAppOpenAPI(@Value("${server.servlet.context-path}") String contextPath) {
        return new OpenAPI()
                .addServersItem(new Server().url(contextPath))
                .info(new io.swagger.v3.oas.models.info.Info().title("Miniapp Microservices Documentation")
                        .description("Miniapp Microservices Documentation")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .components(new Components().addSecuritySchemes("basicScheme", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .externalDocs(new ExternalDocumentation().description("Miniapp Microservices Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }
}
