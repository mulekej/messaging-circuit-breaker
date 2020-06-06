package com.eric.mulek.messagingcircuitbreaker

import com.eric.mulek.messagingcircuitbreaker.config.MessagingCircuitBreakerConfig
import com.eric.mulek.messagingcircuitbreaker.event.MessagingCircuitBreakerListener
import com.eric.mulek.messagingcircuitbreaker.policy.MessagingCircuitBreakerPolicy
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.Lifecycle
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = MessagingCircuitBreakerConfig)
class MessagingCircuitBreakerIntegrationSpec extends Specification {

    @Autowired
    MessagingCircuitBreakerListener systemUnderTest

    @SpringBean
    MessagingCircuitBreakerPolicy mockPolicy = Mock()
    @SpringBean
    Lifecycle mockLifecycle = Mock()

    void "All the beans should wire up correctly"() {
        when:
        def result = systemUnderTest

        then:
        !result
    }
}
