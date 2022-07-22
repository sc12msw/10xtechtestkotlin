package uk.tojourn.xpostkafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class XpostkafkaApplication

fun main(args: Array<String>) {
    runApplication<XpostkafkaApplication>(*args)
}
