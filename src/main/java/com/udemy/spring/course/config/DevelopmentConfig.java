package com.udemy.spring.course.config;

import com.udemy.spring.course.services.EmailService;
import com.udemy.spring.course.services.MockEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevelopmentConfig {

    public static String CREATE = "create";

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean initializeDatabase(){
        return CREATE.equals(strategy);
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
