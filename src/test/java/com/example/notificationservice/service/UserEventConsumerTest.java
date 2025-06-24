//package com.example.notificationservice.service;
//
//import com.example.shared.dto.UserEvent;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.test.annotation.DirtiesContext;
//
//import static org.mockito.Mockito.timeout;
//import static org.mockito.Mockito.verify;
//
//@SpringBootTest
//@DirtiesContext
//@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
//class UserEventConsumerTest {
//
//    @Autowired
//    private UserEventConsumer userEventConsumer;
//
//    @MockBean
//    private EmailService emailService;
//
//    @Test
//    void consumeUserCreatedEvent() throws Exception {
//        UserEvent event = new UserEvent(UserEvent.EventType.CREATED, "test@example.com", "Test User");
//        userEventConsumer.consumeUserEvent(event);
//
//        verify(emailService, timeout(5000)).sendUserCreatedEmail("test@example.com", "Test User");
//    }
//
//    @Test
//    void consumeUserDeletedEvent() throws Exception {
//        UserEvent event = new UserEvent(UserEvent.EventType.DELETED, "test@example.com", "Test User");
//        userEventConsumer.consumeUserEvent(event);
//
//        verify(emailService, timeout(5000)).sendUserDeletedEmail("test@example.com", "Test User");
//    }
//}