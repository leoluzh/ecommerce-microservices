package com.lambdasys.ecommerce.product.client;

import com.lambdasys.ecommerce.product.api.ProductService;
import com.lambdasys.ecommerce.product.component.ProductAlternateServerComponent;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="product-proxy",url = "http://localhost:8080"
        //, fallback = ProductAlternateServerComponent.class
)
public interface ProductServiceProxy extends ProductService {

}
