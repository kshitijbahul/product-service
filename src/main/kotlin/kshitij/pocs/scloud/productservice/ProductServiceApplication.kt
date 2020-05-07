package kshitij.pocs.scloud.productservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope

@SpringBootApplication
@EnableDiscoveryClient//To be discovered by Eureka
@RefreshScope
class ProductServiceApplication

fun main(args: Array<String>){
    runApplication<ProductServiceApplication>(*args)
}