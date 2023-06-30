package com.example.SharedSpaces.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

// Define the OpenAPI specification for the API
@OpenAPIDefinition(
        // Define general information about the API
        info = @Info(
                contact = @Contact(name = "SharedSpaces", email = "e19129@eng.pdn.ac.lk", url = ""),
                description = "SharedSpaces",
                title = "",
                version = "1.0",
                license = @License(name = "Licence name", url = "https://some-url.com"),
                termsOfService = "Terms of service"
        ),
        // Define the servers where the API is hosted
        servers = {
                @Server(description = "LocalHost", url = "http://localhost:8080")
        },
        // Define the security requirements for the API
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)

// Define the security scheme for the API
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}