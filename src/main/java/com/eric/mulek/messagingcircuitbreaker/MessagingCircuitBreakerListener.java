package com.eric.mulek.messagingcircuitbreaker;

import com.eric.mulek.messagingcircuitbreaker.policy.MessagingCircuitBreakerPolicy;
import com.eric.mulek.messagingcircuitbreaker.threshold.MessagingCircuitBreakerThreshold;
import org.springframework.context.event.EventListener;

public class MessagingCircuitBreakerListener {

    private MessagingCircuitBreakerPolicy breakerPolicy;
    private MessagingCircuitBreakerThreshold breakerThreshold;

    public MessagingCircuitBreakerListener(MessagingCircuitBreakerPolicy breakerPolicy, MessagingCircuitBreakerThreshold breakerThreshold) {
        this.breakerPolicy = breakerPolicy;
        this.breakerThreshold = breakerThreshold;
    }

    @EventListener
    public void eventListener(MessagingCircuitBreakerEvent event) {
        if (breakerThreshold.thresholdIsMet(event)) {
            breakerPolicy.process();
        } else {
            breakerPolicy.clear();
        }
    }
}
