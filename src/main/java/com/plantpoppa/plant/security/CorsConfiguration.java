package com.plantpoppa.plant.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://plantpoppa.com", "http://localhost:5173/")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");// enable CORS for plantpoppa.com and localhost
            }
        };
    }
}
