package com.eric.mulek.messagingcircuitbreaker.policy;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("messaging.circuit-breaker.policy.exponential-backoff")
public class ExponentialBackoffPolicyConfiguration {

    private int initialWaitPeriodInSeconds = 1;
    private int maxWaitPeriodInSeconds = 100;

    public int getInitialWaitPeriodInSeconds() {
        return initialWaitPeriodInSeconds;
    }

    public void setInitialWaitPeriodInSeconds(int initialWaitPeriodInSeconds) {
        this.initialWaitPeriodInSeconds = initialWaitPeriodInSeconds;
    }

    public int getMaxWaitPeriodInSeconds() {
        return maxWaitPeriodInSeconds;
    }

    public void setMaxWaitPeriodInSeconds(int maxWaitPeriodInSeconds) {
        this.maxWaitPeriodInSeconds = maxWaitPeriodInSeconds;
    }
}
