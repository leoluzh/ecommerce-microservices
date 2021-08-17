package com.lambdasys.ecommerce.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EcommerceProductMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProductMicroserviceApplication.class, args);
	}

}
