package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.domain.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Request request){
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromRequest(request);
        sendEmail(simpleMailMessage);
    }

    @Override
    public void sendNewPasswordEmail(Customer customer, String newPassword){
        SimpleMailMessage simpleMailMessage = prepareNewPasswordEmail(customer, newPassword);
        sendEmail(simpleMailMessage);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Customer customer, String newPassword){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("New Password");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("New password: "+ newPassword);
        return simpleMailMessage;
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromRequest(Request request){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(request.getCustomer().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Confirmed request! Code: " + request.getId());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(request.toString());
        return simpleMailMessage;
    }

}
