package com.eric.mulek.messagingcircuitbreaker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("messaging.circuit-breaker.threshold")
public class MessagingCircuitBreakerThresholdProperties {

    private ThresholdType type;
    private int consecutiveErrorsThreshold = 5;
    private AverageTimeBetweenErrors averageTimeBetweenErrors;

    public ThresholdType getType() {
        return type;
    }

    public void setType(ThresholdType type) {
        this.type = type;
    }

    public int getConsecutiveErrorsThreshold() {
        return consecutiveErrorsThreshold;
    }

    public void setConsecutiveErrorsThreshold(int consecutiveErrorsThreshold) {
        this.consecutiveErrorsThreshold = consecutiveErrorsThreshold;
    }

    public AverageTimeBetweenErrors getAverageTimeBetweenErrors() {
        return averageTimeBetweenErrors;
    }

    public void setAverageTimeBetweenErrors(AverageTimeBetweenErrors averageTimeBetweenErrors) {
        this.averageTimeBetweenErrors = averageTimeBetweenErrors;
    }

    public static class AverageTimeBetweenErrors {
        private int maxWindowSize = 10;
        private int minWindowSize = 1;
        private int thresholdInMilliseconds = 5000;

        public int getMaxWindowSize() {
            return maxWindowSize;
        }

        public void setMaxWindowSize(int maxWindowSize) {
            this.maxWindowSize = maxWindowSize;
        }

        public int getMinWindowSize() {
            return minWindowSize;
        }

        public void setMinWindowSize(int minWindowSize) {
            this.minWindowSize = minWindowSize;
        }

        public int getThresholdInMilliseconds() {
            return thresholdInMilliseconds;
        }

        public void setThresholdInMilliseconds(int thresholdInMilliseconds) {
            this.thresholdInMilliseconds = thresholdInMilliseconds;
        }
    }
}
