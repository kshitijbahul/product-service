package com.kshitij.pocs.scloud.product;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class Application {
    private static final String queueName = "product-queue";
    static final String topicExchange = "product-update";
    private static final String routingKey = "product.update.#";
    public static void main(String[] args) {
            SpringApplication.run(Application.class,args);
    }

    @Bean
    Queue createQueue(){
        return new Queue(queueName,Boolean.FALSE);
    }
    @Bean
    TopicExchange exchange(){
        return new TopicExchange(topicExchange);
    }
    @Bean
    Binding binding(Queue queue,TopicExchange topicExchange){
        //Any message sent to the exchange with routing key(beginning with product.update) are sent to the queue
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
    }

}
