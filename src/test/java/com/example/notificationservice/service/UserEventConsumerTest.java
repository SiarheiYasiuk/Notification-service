package com.example.notificationservice.service;

import com.example.shared.dto.UserEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserEventConsumerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserEventConsumer userEventConsumer;

    @Test
    void consumeUserEvent_ShouldSendCreatedEmailForCreatedEvent() throws Exception {
        UserEvent event = new UserEvent(UserEvent.EventType.CREATED, "test@example.com", "Test User");

        userEventConsumer.consumeUserEvent(event);

        verify(emailService).sendUserCreatedEmail("test@example.com", "Test User");
    }

    @Test
    void consumeUserEvent_ShouldSendDeletedEmailForDeletedEvent() throws Exception {
        UserEvent event = new UserEvent(UserEvent.EventType.DELETED, "test@example.com", "Test User");

        userEventConsumer.consumeUserEvent(event);

        verify(emailService).sendUserDeletedEmail("test@example.com", "Test User");
    }

    @Test
    void consumeUserEvent_ShouldLogErrorWhenEmailFails() throws Exception {
        UserEvent event = new UserEvent(UserEvent.EventType.CREATED, "test@example.com", "Test User");
        doThrow(new RuntimeException("SMTP error")).when(emailService).sendUserCreatedEmail(any(), any());

        userEventConsumer.consumeUserEvent(event);

        verify(emailService).sendUserCreatedEmail("test@example.com", "Test User");
    }
}