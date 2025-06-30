package com.example.notificationservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    private EmailService emailService;

    @Test
    void sendUserCreatedEmail_ShouldSendEmail() throws MessagingException {
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendUserCreatedEmail("test@example.com", "Test User");

        verify(mailSender).send(mimeMessage);
    }

    @Test
    void sendUserDeletedEmail_ShouldSendEmail() throws MessagingException {
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendUserDeletedEmail("test@example.com", "Test User");

        verify(mailSender).send(mimeMessage);
    }

    @Test
    void sendEmail_ShouldSetCorrectParameters() throws MessagingException {
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendEmail("to@example.com", "Subject", "Content");

        verify(mimeMessage).setFrom("noreply@example.com");
        verify(mimeMessage).setRecipients(MimeMessage.RecipientType.TO, "to@example.com");
        verify(mimeMessage).setSubject("Subject");
        verify(mimeMessage).setText("Content", "UTF-8");
    }
}