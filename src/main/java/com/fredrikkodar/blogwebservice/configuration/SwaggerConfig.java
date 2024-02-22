package com.fredrikkodar.blogwebservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Fredrik",
                        email = "frdk@live.se"
                ),
                description = "OpenApi documentation for Spring Security",
                title = "API documentation - blog-webservice",
                version = "1.0",
                license = @License(
                        name = "MIT- License"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:5000",
                        description = "Local server"
                ),
                @Server(
                        url = "http://blog-webservice-env.eba-pycug5mz.eu-north-1.elasticbeanstalk.com",
                        description = "Production server"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth")

        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Connect to get the JWT token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JTW",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}