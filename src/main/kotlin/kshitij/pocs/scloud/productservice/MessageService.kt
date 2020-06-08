package kshitij.pocs.scloud.productservice

import com.google.gson.Gson
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.MessagePropertiesBuilder
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class MessageProducerService(val rabbitTemplate: RabbitTemplate){

    val logger = LoggerFactory.getLogger(MessageProducerService::class.java)

    @Value("\${rabbit.product.topicExchange}")
    lateinit var topicName:String

    @Autowired
    lateinit var gson:Gson;

    fun sendMessage(product: Product,isDeleted: Boolean,routingKey:String){
        logger.info("Going to send message to Rabbit on the the topic $topicName")
        val messageProperties = MessagePropertiesBuilder.newInstance()
                                    .setContentType(MediaType.APPLICATION_JSON_VALUE)
                                    .setMessageId(Random(Int.MAX_VALUE).toString()).build()

       /*val messageBuilder = MessageBuilder
                                    .withBody()
                                    //.andProperties(messageProperties)
                                    .build()*/
        //rabbitTemplate.convertAndSend(topicName,routingKey,gson.toJson(ProductUpdateMessage(product,isDeleted)).toByteArray())

        rabbitTemplate.convertAndSend(topicName,routingKey, MessageBuilder.withPayload(gson.toJson(ProductUpdateMessage(product, isDeleted))).build())
    }
}
