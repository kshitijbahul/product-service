package kshitij.pocs.scloud.productservice

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MessageProducerService(val rabbitTemplate: RabbitTemplate){

    val logger = LoggerFactory.getLogger(MessageProducerService::class.java)

    @Value("\${rabbit.product.topicExchange}")
    lateinit var topicName:String

    fun sendMessage(product: Product,isDeleted: Boolean,routingKey:String){
        logger.info("Going to send message to Rabbit on the the topic {}",topicName)
        rabbitTemplate.convertAndSend(topicName,routingKey,ProductUpdateMessage(product,isDeleted))
    }
}