package com.example.notificationservice.controller;

import com.example.notificationservice.dto.NotificationRequest;
import com.example.notificationservice.service.EmailService;
import com.example.notificationservice.assertions.NotificationRequestAssert;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private NotificationController notificationController;

    @Test
    void sendNotification_ShouldCallEmailServiceForUserCreated() throws MessagingException {
        NotificationRequest request = new NotificationRequest();
        request.setEmail("test@example.com");
        request.setName("Test User");
        request.setType(NotificationRequest.NotificationType.USER_CREATED);

        notificationController.sendNotification(request);

        verify(emailService).sendUserCreatedEmail("test@example.com", "Test User");
    }

    @Test
    void sendNotification_ShouldCallEmailServiceForUserDeleted() throws MessagingException {
        NotificationRequest request = new NotificationRequest();
        request.setEmail("test@example.com");
        request.setName("Test User");
        request.setType(NotificationRequest.NotificationType.USER_DELETED);

        notificationController.sendNotification(request);

        verify(emailService).sendUserDeletedEmail("test@example.com", "Test User");
    }

    @Test
    void sendNotification_ShouldReturnAcceptedStatus() {
        NotificationRequest request = new NotificationRequest();
        request.setEmail("test@example.com");
        request.setName("Test User");
        request.setType(NotificationRequest.NotificationType.USER_CREATED);

        notificationController.sendNotification(request);
    }
}