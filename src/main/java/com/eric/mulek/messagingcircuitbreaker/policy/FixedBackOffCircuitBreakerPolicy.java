package com.eric.mulek.messagingcircuitbreaker.policy;

import com.eric.mulek.messagingcircuitbreaker.util.ThreadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.Lifecycle;

import java.util.concurrent.locks.ReentrantLock;

public class FixedBackOffCircuitBreakerPolicy implements MessagingCircuitBreakerPolicy {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReentrantLock lock;
    private FixedBackoffPolicyConfiguration configuration;
    private Lifecycle endpointRegistry;
    private ThreadHelper threadHelper;

    public FixedBackOffCircuitBreakerPolicy(ReentrantLock lock, FixedBackoffPolicyConfiguration configuration, Lifecycle endpointRegistry, ThreadHelper threadHelper) {
        this.lock = lock;
        this.configuration = configuration;
        this.endpointRegistry = endpointRegistry;
        this.threadHelper = threadHelper;
    }

    @Override
    public void process() {
        if (lock.tryLock()) {
            try {
                endpointRegistry.stop();
                threadHelper.sleep(configuration.getBackOffWindowInSeconds() * 1000);
            } catch (InterruptedException e) {
                logger.warn("event=MessagingListenerSleepInterrupted", e);
            } finally {
                endpointRegistry.start();
                lock.unlock();
            }
        }
    }

    @Override
    public void clear() {
        if (lock.tryLock()) {
            try {
                endpointRegistry.start();
            } finally {
                lock.unlock();
            }
        }
    }
}
