package com.eric.mulek.messagingcircuitbreaker.threshold

import spock.lang.Specification

class AverageTimeBetweenErrorThresholdSpec extends Specification {

    AverageTimeBetweenErrorThreshold systemUnderTest

    void setup() {
        systemUnderTest = new AverageTimeBetweenErrorThreshold()
    }

    void "Name"() {
        when:
        def result = systemUnderTest

        then:
        !result
    }
}
