package com.example.notificationservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${notification.email.from}")
    private String fromEmail;

    @Value("${notification.email.subject.created}")
    private String createdSubject;

    @Value("${notification.email.subject.deleted}")
    private String deletedSubject;

    public void sendUserCreatedEmail(String toEmail, String name) throws MessagingException {
        String content = "Здравствуйте, " + name + "! Ваш аккаунт на сайте ваш сайт был успешно создан.";
        sendEmail(toEmail, createdSubject, content);
    }

    public void sendUserDeletedEmail(String toEmail, String name) throws MessagingException {
        String content = "Здравствуйте, " + name + "! Ваш аккаунт был удалён.";
        sendEmail(toEmail, deletedSubject, content);
    }

    private void sendEmail(String toEmail, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(content, false);

        mailSender.send(message);
    }
}