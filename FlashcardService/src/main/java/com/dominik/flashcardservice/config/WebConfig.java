package com.dominik.flashcardservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Umożliwia dostęp do wszystkich endpointów
                .allowedOrigins("http://localhost:5174") // Dozwolone źródła (zmień na swoje źródło)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Dozwolone metody HTTP
                .allowedHeaders("*"); // Dozwolone nagłówki
//                .allowCredentials(true); // Pozwolenie na przekazywanie ciasteczek
    }
}
