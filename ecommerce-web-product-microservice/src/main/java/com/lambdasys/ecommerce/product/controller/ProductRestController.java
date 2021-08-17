package com.lambdasys.ecommerce.product.controller;

import com.lambdasys.ecommerce.product.dto.ProductDto;
import com.lambdasys.ecommerce.product.client.ProductServiceProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@EnableFeignClients(basePackageClasses=ProductServiceProxy.class)
@ComponentScan(basePackageClasses = ProductServiceProxy.class)
@CrossOrigin
@RestController
@RequestMapping("/api/v1/productsweb")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductRestController {

    private final ProductServiceProxy productServiceProxy;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ProductDto>>> getAllProducts(){
        return productServiceProxy.getAllProducts();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductDto>> getProduct(@PathVariable("id") final String id){
        return productServiceProxy.getProduct(id);
    }

}
