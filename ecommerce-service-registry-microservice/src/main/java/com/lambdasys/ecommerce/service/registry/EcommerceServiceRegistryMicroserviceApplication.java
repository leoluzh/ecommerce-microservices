package com.lambdasys.ecommerce.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EcommerceServiceRegistryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceServiceRegistryMicroserviceApplication.class, args);
	}

}
