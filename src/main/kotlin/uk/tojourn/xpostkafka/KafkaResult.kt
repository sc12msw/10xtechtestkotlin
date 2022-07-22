package uk.tojourn.xpostkafka

enum class Operation{
    WRITE_SINGLE_MESSAGE
}

sealed class KafkaResult {
    data class Success(val operation: Operation) : KafkaResult()
    data class Failed(val operation: Operation, val errorMessage : String) : KafkaResult()
}
