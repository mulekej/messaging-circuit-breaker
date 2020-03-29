package com.eric.mulek.messagingcircuitbreaker;

import org.springframework.context.ApplicationEvent;

import java.time.Instant;

public class MessagingCircuitBreakerEvent extends ApplicationEvent {

    public MessagingCircuitBreakerEvent(Boolean success) {
        super(success);
    }

    public boolean isSuccessful() {
        return (Boolean) getSource();
    }

    public Instant getEventInstant() {
        return Instant.ofEpochMilli(this.getTimestamp());
    }
}
