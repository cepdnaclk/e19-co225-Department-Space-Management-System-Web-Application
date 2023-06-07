package com.example.SharedSpaces.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "SharedSpaces",
                        email = "e19129@eng.pdn.ac.lk",
                        url = ""
                ),
                description = "SharedSpaces",
                title = "",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "LocalHost",
                        url = "http://localhost:8080"
                ),
        }
)

public class OpenApiConfig {
}
