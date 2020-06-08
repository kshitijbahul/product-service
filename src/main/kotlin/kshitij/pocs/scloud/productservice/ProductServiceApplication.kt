package kshitij.pocs.scloud.productservice

import com.google.gson.Gson
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient//To be discovered by Eureka
@RefreshScope
open class ProductServiceApplication

fun main(args: Array<String>){
    runApplication<ProductServiceApplication>(*args)
}
@Bean
fun gson(): Gson{
    return Gson()
}