package com.kshitij.pocs.scloud.product.service;

import com.kshitij.pocs.scloud.product.Application;
import com.kshitij.pocs.scloud.product.entity.Product;
import com.kshitij.pocs.scloud.product.message.ProductUpdateMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MessageProducerService {
    private final String topicExchangeName="product-update";
    private final RabbitTemplate rabbitTemplate;

    public MessageProducerService(final RabbitTemplate template){
        this.rabbitTemplate=template;
    }

    public void sendMessage(Product product, Boolean isProductDeleted,String routingKey){

        log.info("Entered the sendMessage");
        this.rabbitTemplate.convertAndSend(topicExchangeName ,routingKey,new ProductUpdateMessage(product,isProductDeleted));
    }
}