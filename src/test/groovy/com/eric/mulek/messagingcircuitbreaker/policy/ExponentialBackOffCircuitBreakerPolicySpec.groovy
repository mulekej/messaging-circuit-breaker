package com.eric.mulek.messagingcircuitbreaker.policy

import spock.lang.Specification

class ExponentialBackOffCircuitBreakerPolicySpec extends Specification {

    ExponentialBackOffCircuitBreakerPolicy systemUnderTest

    void setup() {
        systemUnderTest = new ExponentialBackOffCircuitBreakerPolicy()
    }

    void "Name"() {
        when:
        def result = systemUnderTest

        then:
        !result
    }
}
