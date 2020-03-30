package com.eric.mulek.messagingcircuitbreaker.config;

import com.eric.mulek.messagingcircuitbreaker.policy.ExponentialBackoffPolicyConfiguration;
import com.eric.mulek.messagingcircuitbreaker.policy.FixedBackoffPolicyConfiguration;
import com.eric.mulek.messagingcircuitbreaker.threshold.AverageTimeBetweenErrorThreshold;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({FixedBackoffPolicyConfiguration.class, ExponentialBackoffPolicyConfiguration.class, AverageTimeBetweenErrorThreshold.class})
@Configuration
public class TempConfig {
}
