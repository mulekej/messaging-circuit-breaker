package com.eric.mulek.messagingcircuitbreaker.policy;

public interface MessagingCircuitBreakerPolicy {

    void process();

    void clear();
}
