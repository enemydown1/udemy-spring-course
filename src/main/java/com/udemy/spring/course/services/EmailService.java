package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.domain.Request;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendOrderConfirmationEmail(Request request);

    void sendEmail(SimpleMailMessage message);

    void sendNewPasswordEmail(Customer customer, String newPassword);

    void sendOrderConfirmationHtmlEmail(Request request);

    void sendHtmlEmail(MimeMessage message);
}
