package uk.tojourn.xpostkafka

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("kafka")
class KafkaController (val kafkaProducer: KafkaProducer) {

    @PostMapping("/{topic}")
    fun createTopic(@PathVariable topic: String, @RequestBody kafkaMessage: KafkaMessage): ResponseEntity<String> {
       return kotlin.runCatching {
            kafkaProducer.sendMessage(kafkaMessage, topic)
            ResponseEntity.status(202).body("hello")
        }.getOrElse {
            ResponseEntity.status(500).body("hello")
        }
    }
}