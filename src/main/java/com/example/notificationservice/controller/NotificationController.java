package com.example.notificationservice.controller;

import com.example.notificationservice.dto.NotificationRequest;
import com.example.notificationservice.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification Management", description = "Endpoints for sending notifications")
public class NotificationController {
    private final EmailService emailService;

    @Operation(summary = "Send notification", description = "Sends email notification based on type")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Notification accepted for processing"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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