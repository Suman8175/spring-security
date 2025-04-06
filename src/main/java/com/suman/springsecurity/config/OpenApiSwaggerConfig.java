package com.suman.springsecurity.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Suman Devkota",
                        email = "sumandevkota.work@gmail.com",
                        url = "www.devkotasuman.com.np"
                ),
                description = "These API are for learning",
                title = "Test Point for API",
                version = "1.0"
        ),
        servers = {@Server(description = "LocalHost Environment",url = "http://localhost:8080"),
                    @Server(description = "Production Environment",url = "https://prod:8080")
                 }
)
public class OpenApiSwaggerConfig {
}
