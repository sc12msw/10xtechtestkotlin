package uk.tojourn.xpostkafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class Config {

    @Bean
    fun producerFactory() = DefaultKafkaProducerFactory<String, String>(senderProps)

    val senderProps = mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:29092",
        ProducerConfig.LINGER_MS_CONFIG to 10,
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
    )

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, String>) = KafkaTemplate(producerFactory)
}