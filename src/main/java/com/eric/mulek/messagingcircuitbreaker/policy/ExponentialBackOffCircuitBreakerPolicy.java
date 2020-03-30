package com.eric.mulek.messagingcircuitbreaker.policy;

import com.eric.mulek.messagingcircuitbreaker.util.ThreadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.Lifecycle;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.locks.ReentrantLock;

public class ExponentialBackOffCircuitBreakerPolicy implements MessagingCircuitBreakerPolicy {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExponentialBackoffPolicyConfiguration configuration;
    private int currentWaitPeriodInSeconds;
    private final ReentrantLock lock;
    private ThreadHelper threadHelper;
    private Lifecycle endpointRegistry;

    public ExponentialBackOffCircuitBreakerPolicy(ExponentialBackoffPolicyConfiguration configuration,
                                                  ReentrantLock lock,
                                                  ThreadHelper threadHelper,
                                                  Lifecycle endpointRegistry) {
        this.configuration = configuration;
        this.lock = lock;
        this.threadHelper = threadHelper;
        this.endpointRegistry = endpointRegistry;

        currentWaitPeriodInSeconds = configuration.getInitialWaitPeriodInSeconds();
    }

    @Override
    @Async
    public void process() {
        if (lock.tryLock()) {
            try {
                endpointRegistry.stop();
                threadHelper.sleep(currentWaitPeriodInSeconds*1000);
            } catch (InterruptedException e) {
                logger.warn("event=MessagingListenerSleepInterrupted", e);
            } finally {
                incrementWaitPeriod();
                endpointRegistry.start();
                lock.unlock();
            }
        }
    }

    private void incrementWaitPeriod() {
        int increasedWaitPeriod = currentWaitPeriodInSeconds * configuration.getInitialWaitPeriodInSeconds();
        currentWaitPeriodInSeconds = Math.min(configuration.getMaxWaitPeriodInSeconds(), increasedWaitPeriod);
    }

    @Override
    public void clear() {
        if (lock.tryLock()) {
            try {
                currentWaitPeriodInSeconds = configuration.getInitialWaitPeriodInSeconds();
            } finally {
                lock.unlock();
            }
        }
    }
}
