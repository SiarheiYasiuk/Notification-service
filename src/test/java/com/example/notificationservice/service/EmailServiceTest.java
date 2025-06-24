package com.example.notificationservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService; // здесь не мок, а настоящий с внедрённым mailSender

    @BeforeEach
    void setup() {
        MimeMessage mimeMessage = new MimeMessage((Session) null);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        injectField(emailService, "fromEmail", "test@from.com");
        injectField(emailService, "createdSubject", "Создано");
        injectField(emailService, "deletedSubject", "Удалено");
    }

    @Test
    void sendUserCreatedEmail_shouldSendSuccessfully() throws MessagingException {
        emailService.sendUserCreatedEmail("test@example.com", "Test User");
        verify(mailSender, times(1)).send(any(MimeMessage.class));
    }

    @Test
    void sendUserDeletedEmail_shouldSendSuccessfully() throws MessagingException {
        emailService.sendUserDeletedEmail("test@example.com", "Test User");
        verify(mailSender, times(1)).send(any(MimeMessage.class));
    }

    private void injectField(Object target, String fieldName, String value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

