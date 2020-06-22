package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.domain.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

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

    @Override
    public void sendOrderConfirmationHtmlEmail(Request request) {
        try {
            MimeMessage mimeMessage = prepareMimeMessageFromRequest(request);
            sendHtmlEmail(mimeMessage);
        } catch (MessagingException messagingException){
            sendOrderConfirmationEmail(request);
        }
    }

    protected MimeMessage prepareMimeMessageFromRequest(Request request) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(request.getCustomer().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Confirmed Request! Code: "+request.getId());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplateRequest(request), true);
        return mimeMessage;
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

    protected String htmlFromTemplateRequest(Request request){
        Context context = new Context();
        context.setVariable("request", request);
        return templateEngine.process("email/requestConfirmation", context);
    }
}
