package com.famisanar.arquitectura.department.adapter.in.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Arquitectura",
        version = "1.0",
        description = "API creada como base para los proyectos backend Java",
        termsOfService = "https://www.example.com/terms",
        contact = @Contact(
            name = "Pablo Garzon",
            email = "pablo.garzon@famisanar.com",
            url = "https://www.example.com/contact"
        ),
        license = @License(
            name = "Licencia de mi API Famisanar",
            url = "https://www.example.com/license"
        )
    )
)
public class OpenApiConfig {
    
     @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/**")
                .build();
    }
}
