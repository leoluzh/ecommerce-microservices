package com.lambdasys.ecommerce.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class EcommerceApiGatewayMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApiGatewayMicroserviceApplication.class, args);
	}

}
