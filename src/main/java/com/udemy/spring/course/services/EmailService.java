package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Request;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendOrderConfirmationEmail(Request request);

    void sendEmail(SimpleMailMessage message);
}
