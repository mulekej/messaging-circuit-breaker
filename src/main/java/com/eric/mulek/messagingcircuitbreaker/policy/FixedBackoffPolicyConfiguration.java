package com.eric.mulek.messagingcircuitbreaker.policy;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("messaging.circuit-breaker.policy.fixed-backoff")
public class FixedBackoffPolicyConfiguration {

    private int backOffWindowInSeconds = 3000;

    public int getBackOffWindowInSeconds() {
        return backOffWindowInSeconds;
    }

    public void setBackOffWindowInSeconds(int backOffWindowInSeconds) {
        this.backOffWindowInSeconds = backOffWindowInSeconds;
    }
}
