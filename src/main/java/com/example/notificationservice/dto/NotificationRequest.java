package com.example.notificationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationRequest {
    public enum NotificationType {
        USER_CREATED, USER_DELETED
    }

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    private NotificationType type;
}