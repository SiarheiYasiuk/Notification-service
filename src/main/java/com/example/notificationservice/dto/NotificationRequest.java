package com.example.notificationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for sending notifications")
public class NotificationRequest {
    public enum NotificationType {
        @Schema(description = "Notification about user creation")
        USER_CREATED,

        @Schema(description = "Notification about user deletion")
        USER_DELETED
    }

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Schema(
            description = "Recipient email address",
            example = "user@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            pattern = "^[A-Za-z0-9+_.-]+@(.+)$"
    )
    private String email;

    @NotBlank(message = "Name is mandatory")
    @Schema(
            description = "Recipient name",
            example = "John Doe",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 2,
            maxLength = 100
    )
    private String name;

    @NotNull(message = "Notification type is mandatory")
    @Schema(
            description = "Type of notification to send",
            example = "USER_CREATED",
            requiredMode = Schema.RequiredMode.REQUIRED,
            implementation = NotificationType.class
    )
    private NotificationType type;

    @Schema(
            description = "Additional context for notification",
            example = "Account was created at 2023-01-01",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String context;
}