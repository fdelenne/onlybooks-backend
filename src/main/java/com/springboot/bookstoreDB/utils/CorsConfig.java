package com.springboot.bookstoreDB.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig
{
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                registry.addMapping("/api/v1/item/**")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedOrigins("http://localhost:8080")
                    .allowedOrigins("http://172.18.34.182:8080")
                    .maxAge(3600);
            }
        };
    }
}
