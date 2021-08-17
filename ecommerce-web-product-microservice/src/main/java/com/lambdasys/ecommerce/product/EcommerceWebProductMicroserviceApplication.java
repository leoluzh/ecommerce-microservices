package com.lambdasys.ecommerce.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EcommerceWebProductMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceWebProductMicroserviceApplication.class, args);
    }

}
