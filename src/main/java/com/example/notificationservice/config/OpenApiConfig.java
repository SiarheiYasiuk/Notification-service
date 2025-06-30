package com.example.notificationservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI notificationsMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Notification Service API")
                        .description("API for sending notifications")
                        .version("1.0"));
    }
}