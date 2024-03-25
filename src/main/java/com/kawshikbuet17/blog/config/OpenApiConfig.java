package com.kawshikbuet17.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot - Blog App API",
                description = "This is a user friendly web api application where users can create accounts, can read or write blog posts.",
                summary = "Mainly CRUD Operations are done in this web api project",
                termsOfService = "T&C",
                contact = @Contact(
                        name = "Kawshik Kumar Paul",
                        email = "kawshikbuet17@gmail.com"
                ),
                license = @License(
                        name = "kawshikbuet17"
                ),
                version = "v1"
        ),
        servers = {
                @Server(
                        description = "Dev",
                        url = "http://localhost:9090"
                ),
                @Server(
                        description = "Test",
                        url = "http://localhost:9090"
                )
        },

        //this will lock all the apis
        security = @SecurityRequirement(
                name = "auth"
        )
        //if we want to lock any individual api, delete this one and just put the annotation @SecurityRequirement(name="the_name") above the method
)


@SecurityScheme(
        name = "auth",
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "In request headers, Authorization: Bearer MY_JWT_TOKEN. In Swagger, just put the value of MY_JWT_TOKEN"
)
public class OpenApiConfig {
}
