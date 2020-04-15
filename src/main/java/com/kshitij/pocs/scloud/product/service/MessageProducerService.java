package com.kshitij.pocs.scloud.product.service;

import com.kshitij.pocs.scloud.product.entity.Product;
import com.kshitij.pocs.scloud.product.message.ProductUpdateMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MessageProducerService {
    @Value("${rabbit.product.topicExchange}")
    private String topicExchangeName;
    private final RabbitTemplate rabbitTemplate;

    public MessageProducerService(final RabbitTemplate template){
        this.rabbitTemplate=template;
    }

    public void sendMessage(Product product, Boolean isProductDeleted,String routingKey){

        log.info("Entered the sendMessage");
        this.rabbitTemplate.convertAndSend(this.topicExchangeName ,routingKey,new ProductUpdateMessage(product,isProductDeleted));
    }
}