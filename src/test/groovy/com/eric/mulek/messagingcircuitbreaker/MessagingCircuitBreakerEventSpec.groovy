package com.eric.mulek.messagingcircuitbreaker

import com.eric.mulek.messagingcircuitbreaker.event.MessagingCircuitBreakerEvent
import spock.lang.Specification

class MessagingCircuitBreakerEventSpec extends Specification {

    MessagingCircuitBreakerEvent systemUnderTest

    void setup() {
        systemUnderTest = new MessagingCircuitBreakerEvent(false)
    }

    void "Name"() {
        when:
        def result = systemUnderTest

        then:
        !result
    }
}
