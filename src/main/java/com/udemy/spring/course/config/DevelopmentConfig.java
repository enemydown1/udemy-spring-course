package com.udemy.spring.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevelopmentConfig {

    @Bean
    public boolean initializeDatabase(){
        return true;
    }
}
