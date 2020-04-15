package com.kshitij.pocs.scloud.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@RefreshScope//to refresh the configrations we need to send a post command to 8082/refresh
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
