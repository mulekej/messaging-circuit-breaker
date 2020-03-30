package com.eric.mulek.messagingcircuitbreaker.threshold;

import com.eric.mulek.messagingcircuitbreaker.MessagingCircuitBreakerEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("messaging.circuit-breaker.threshold.average-time-between-errors")
public class AverageTimeBetweenErrorThreshold implements MessagingCircuitBreakerThreshold {

    private int maxWindowSize = 10;
    private int minWindowSize = 1;
    private long threshold = 5;
    private List<Instant> eventBuffer = new ArrayList<>();

    public boolean thresholdIsMet(MessagingCircuitBreakerEvent event) {
        if (!event.isSuccessful()) {
            addTimeStampToListAndMaintainWindowSize(event.getEventInstant());
        }
        return eventBuffer.size() >= minWindowSize && isThresholdSurpassed();
    }

    private boolean isThresholdSurpassed() {
        ArrayList<Long> temp = new ArrayList<>();
        for (int i = 0; i < eventBuffer.size() - 1;  i++) {
            temp.add(ChronoUnit.MILLIS.between(eventBuffer.get(i + 1), eventBuffer.get(i)));
        }
        Long sum = temp.stream().reduce(0L, Long::sum);
        return (sum / temp.size()) < threshold;
    }

    private void addTimeStampToListAndMaintainWindowSize(Instant instant) {
        eventBuffer.add(instant);
        if (eventBuffer.size() > maxWindowSize) {
            eventBuffer.remove(0);
        }
    }

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

    public long getThreshold() {
        return threshold;
    }

    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }
}
