package com.eric.mulek.messagingcircuitbreaker.policy

import spock.lang.Specification

class ExponentialBackOffCircuitBreakerPolicySpec extends Specification {

    ExponentialBackOffCircuitBreakerPolicy systemUnderTest

    void setup() {
        systemUnderTest = Mock()
    }

    void "Name"() {
        when:
        def result = systemUnderTest

        then:
        !result
    }
}
