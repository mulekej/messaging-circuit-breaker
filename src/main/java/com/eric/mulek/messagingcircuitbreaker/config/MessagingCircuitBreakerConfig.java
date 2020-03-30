package com.eric.mulek.messagingcircuitbreaker.config;

import com.eric.mulek.messagingcircuitbreaker.MessagingCircuitBreakerListener;
import com.eric.mulek.messagingcircuitbreaker.policy.MessagingCircuitBreakerPolicy;
import com.eric.mulek.messagingcircuitbreaker.threshold.AverageTimeBetweenErrorThreshold;
import com.eric.mulek.messagingcircuitbreaker.threshold.ConsecutiveErrorThreshold;
import com.eric.mulek.messagingcircuitbreaker.threshold.MessagingCircuitBreakerThreshold;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.locks.ReentrantLock;

@Configuration
@EnableConfigurationProperties(MessagingCircuitBreakerThresholdProperties.class)
@ConditionalOnProperty(value = "messaging.circuit-breaker.enabled", havingValue = "true", matchIfMissing = true)
public class MessagingCircuitBreakerConfig {

    @Bean
    @Qualifier("circuitBreakerLock")
    ReentrantLock reentrantLock() {
        return new ReentrantLock();
    }

    @Bean
    @ConditionalOnProperty(value = "messaging.circuit-breaker.threshold.type", havingValue = "consecutive_errors", matchIfMissing = true)
    ConsecutiveErrorThreshold consecutiveErrorThreshold(MessagingCircuitBreakerThresholdProperties properties) {
        return new ConsecutiveErrorThreshold(properties.getConsecutiveErrorsThreshold());
    }

    @Bean
    @ConditionalOnProperty(value = "messaging.circuit-breaker.threshold.type", havingValue = "average_time_between_errors")
    AverageTimeBetweenErrorThreshold averageTimeBetweenErrorThreshold(MessagingCircuitBreakerThresholdProperties properties) {
        return new AverageTimeBetweenErrorThreshold(
                properties.getAverageTimeBetweenErrors().getMaxWindowSize(),
                properties.getAverageTimeBetweenErrors().getMinWindowSize(),
                properties.getAverageTimeBetweenErrors().getThresholdInMilliseconds());
    }

    @Bean
    MessagingCircuitBreakerListener messagingCircuitBreakerListener(MessagingCircuitBreakerPolicy messagingCircuitBreakerPolicy,
                                                                    MessagingCircuitBreakerThreshold messagingCircuitBreakerThreshold) {
        return new MessagingCircuitBreakerListener(messagingCircuitBreakerPolicy, messagingCircuitBreakerThreshold);
    }
}
