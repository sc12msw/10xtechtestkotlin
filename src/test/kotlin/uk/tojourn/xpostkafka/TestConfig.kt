package uk.tojourn.xpostkafka

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class TestConfig {

    @Bean
    fun  testRestTemplate() : TestRestTemplate{
        return TestRestTemplate()
    }
}