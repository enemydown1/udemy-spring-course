package com.udemy.spring.course.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.spring.course.domain.BilletPayment;
import com.udemy.spring.course.domain.CardPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(CardPayment.class);
                objectMapper.registerSubtypes(BilletPayment.class);
                super.configure(objectMapper);
            };
        };
        return builder;
    }
}