package com.eric.mulek.messagingcircuitbreaker.policy

import spock.lang.Specification

class FixedBackOffCircuitBreakerPolicySpec extends Specification {

    FixedBackOffCircuitBreakerPolicy systemUnderTest

    void setup() {
        systemUnderTest = new FixedBackOffCircuitBreakerPolicy()
    }

    void "Name"() {
        when:
        def result = systemUnderTest

        then:
        !result
    }
}
