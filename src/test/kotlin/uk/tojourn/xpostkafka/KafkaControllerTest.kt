package uk.tojourn.xpostkafka

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate


internal class KafkaControllerTest {

    private val kafkaProducer = mockk<KafkaProducer>()
    private val kafkaController = KafkaController(kafkaProducer)

    @Test
    fun createTopic() {
        val testTopic = "tenx"
        val testMessage = KafkaMessage("hello")
        every {
            kafkaProducer.sendMessage(testMessage, testTopic)
        } returns "ok"
        val result =  kafkaController.createTopic(
            testTopic,
            KafkaMessage(testMessage.body)
        ).body
        val expected = "Hello world $testTopic with message $testMessage"
        assert(
            result == expected
        )
    }

    @Test
    fun testNullMessage () {
        // TODO
    }
}