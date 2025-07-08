package com.examplenewstack.newstack.config.rabbitMQ.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("arkyndevelop@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        }catch (Exception e){
            System.out.println("Erro ao enviar o E-mail: " + e.getMessage());
        }
    }

    public void sendHtmlMessage(String to, String subject, String htmlContent) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setFrom("arkyndevelop@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }
}
