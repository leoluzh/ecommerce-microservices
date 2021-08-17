package com.lambdasys.ecommerce.product.client;

import com.lambdasys.ecommerce.product.api.ProductService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="product-alternate-proxy",url = "http://localhost:8079")
public interface ProductAlternateServiceProxy extends ProductService {
}
