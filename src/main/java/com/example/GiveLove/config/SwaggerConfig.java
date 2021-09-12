package com.example.GiveLove.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                // config auth
                                .components(new Components()
                                                .addSecuritySchemes("bearer-key-admin",
                                                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer").bearerFormat("JWT"))
                                                .addSecuritySchemes("bearer-key-user",
                                                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer").bearerFormat("JWT")))
                                // config list of server to test
                                .servers(Arrays.asList(new Server().url("http://localhost:8080")))
                                // info
                                .info(new Info().title("Give Love API").description("Sample OpenAPI 3.0")
                                                .contact(new Contact().email("hungamyp@gmail.com").name("Hung")
                                                                .url(""))
                                                .license(new License().name("Apache 2.0")
                                                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                                                .version("1.0.0"));
        }
}
