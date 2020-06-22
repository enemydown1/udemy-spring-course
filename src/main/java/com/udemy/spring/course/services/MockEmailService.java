package com.udemy.spring.course.services;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService{

    private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOGGER.info("Email simulation");
        LOGGER.info(message.toString());
        LOGGER.info("Email sent");
    }

    @Override
    public void sendHtmlEmail(MimeMessage message) {
        LOGGER.info("Email simulation html");
        LOGGER.info(message.toString());
        LOGGER.info("Email sent");
    }
}
