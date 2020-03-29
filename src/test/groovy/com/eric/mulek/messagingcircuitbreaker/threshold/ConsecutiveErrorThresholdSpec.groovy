package com.eric.mulek.messagingcircuitbreaker.threshold

import spock.lang.Specification

class ConsecutiveErrorThresholdSpec extends Specification {

    ConsecutiveErrorThreshold systemUnderTest

    void setup() {
        systemUnderTest = new ConsecutiveErrorThreshold()
    }

    void "Name"() {
        when:
        def result = systemUnderTest

        then:
        !result
    }
}
