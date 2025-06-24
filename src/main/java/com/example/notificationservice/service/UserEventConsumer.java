package com.example.notificationservice.service;

import com.example.shared.dto.UserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeUserEvent(UserEvent event) {
        try {
            switch (event.getEventType()) {
                case CREATED -> emailService.sendUserCreatedEmail(event.getEmail(), event.getName());
                case DELETED -> emailService.sendUserDeletedEmail(event.getEmail(), event.getName());
            }
            log.info("Successfully processed event for user: {}", event.getEmail());
        } catch (Exception e) {
            log.error("Error processing event for user: {}", event.getEmail(), e);
        }
    }
}