package uk.tojourn.xpostkafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(val kafkaTemplate: KafkaTemplate<String, String>) {
    fun sendMessage(message: KafkaMessage, topic: String): KafkaResult {
        kotlin.runCatching {
            val theMessage = kafkaTemplate.send(topic, message.body).get()
            println(theMessage)
            return KafkaResult.Success(Operation.WRITE_SINGLE_MESSAGE)
        }
        .getOrElse { error ->
            return KafkaResult.Failed(Operation.WRITE_SINGLE_MESSAGE, "failed")
        }
    }
}