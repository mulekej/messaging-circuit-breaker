package com.eric.mulek.messagingcircuitbreaker.threshold;

import com.eric.mulek.messagingcircuitbreaker.event.MessagingCircuitBreakerEvent;

public interface MessagingCircuitBreakerThreshold {

    boolean thresholdIsMet(MessagingCircuitBreakerEvent event);
}
