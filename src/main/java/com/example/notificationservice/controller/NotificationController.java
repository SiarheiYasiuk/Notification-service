package com.example.notificationservice.controller;

import com.example.notificationservice.dto.NotificationRequest;
import com.example.notificationservice.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final EmailService emailService;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendNotification(@RequestBody @Valid NotificationRequest request) {
        try {
            switch (request.getType()) {
                case USER_CREATED ->
                        emailService.sendUserCreatedEmail(request.getEmail(), request.getName());
                case USER_DELETED ->
                        emailService.sendUserDeletedEmail(request.getEmail(), request.getName());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to send notification", e);
        }
    }
}