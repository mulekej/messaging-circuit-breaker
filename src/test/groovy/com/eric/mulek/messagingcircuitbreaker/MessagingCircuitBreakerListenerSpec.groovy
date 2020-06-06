package com.eric.mulek.messagingcircuitbreaker

import com.eric.mulek.messagingcircuitbreaker.event.MessagingCircuitBreakerListener
import spock.lang.Specification

class MessagingCircuitBreakerListenerSpec extends Specification {

    MessagingCircuitBreakerListener systemUnderTest

    void setup() {
        systemUnderTest = new MessagingCircuitBreakerListener()
    }

    void "Name"() {
        when:
        def result = systemUnderTest

        then:
        !result
    }
}
