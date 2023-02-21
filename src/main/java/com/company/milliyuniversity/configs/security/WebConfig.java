package com.company.milliyuniversity.configs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 20/02/23 Monday 21:30
 * milliy-university/IntelliJ IDEA
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://national-university.herokuapp.com")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("*")
                .allowCredentials(false).maxAge(3600);
    }
}
