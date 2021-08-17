package com.lambdasys.ecommerce.product.component;

import com.lambdasys.ecommerce.product.client.ProductAlternateServiceProxy;
import com.lambdasys.ecommerce.product.client.ProductServiceProxy;
import com.lambdasys.ecommerce.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@EnableFeignClients(basePackageClasses = ProductAlternateServiceProxy.class)
@ComponentScan(basePackageClasses = ProductAlternateServiceProxy.class)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ProductAlternateServerComponent implements ProductServiceProxy {

    private final ProductServiceProxy productServiceProxy;
    private final ProductAlternateServiceProxy productAlternateServiceProxy;

    @Override
    public ResponseEntity<CollectionModel<EntityModel<ProductDto>>> getAllProducts() {
        log.info("Delegating...");
        return this.productAlternateServiceProxy.getAllProducts();
    }

    @Override
    public ResponseEntity<EntityModel<ProductDto>> getProduct(String id) {
        log.info("Delegating...");
        return this.productAlternateServiceProxy.getProduct(id);
    }
}
