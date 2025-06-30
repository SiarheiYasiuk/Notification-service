package com.example.notificationservice.assertions;

import com.example.notificationservice.dto.NotificationRequest;
import org.assertj.core.api.AbstractAssert;

public class NotificationRequestAssert extends AbstractAssert<NotificationRequestAssert, NotificationRequest> {

    public NotificationRequestAssert(NotificationRequest actual) {
        super(actual, NotificationRequestAssert.class);
    }

    public static NotificationRequestAssert assertThat(NotificationRequest actual) {
        return new NotificationRequestAssert(actual);
    }

    public NotificationRequestAssert hasEmail(String expectedEmail) {
        isNotNull();
        if (!actual.getEmail().equals(expectedEmail)) {
            failWithMessage("Expected email to be <%s> but was <%s>", expectedEmail, actual.getEmail());
        }
        return this;
    }

    public NotificationRequestAssert hasName(String expectedName) {
        isNotNull();
        if (!actual.getName().equals(expectedName)) {
            failWithMessage("Expected name to be <%s> but was <%s>", expectedName, actual.getName());
        }
        return this;
    }

    public NotificationRequestAssert hasType(NotificationRequest.NotificationType expectedType) {
        isNotNull();
        if (actual.getType() != expectedType) {
            failWithMessage("Expected type to be <%s> but was <%s>", expectedType, actual.getType());
        }
        return this;
    }
}