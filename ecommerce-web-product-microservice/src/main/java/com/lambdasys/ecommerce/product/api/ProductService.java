package com.lambdasys.ecommerce.product.api;

import com.lambdasys.ecommerce.product.dto.ProductDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductService {

    @GetMapping(value = "/api/v1/products", produces = "application/hal+json")
    ResponseEntity<CollectionModel<EntityModel<ProductDto>>> getAllProducts();

    @GetMapping(value = "/api/v1/products/{id}", produces = "application/hal+json")
    ResponseEntity<EntityModel<ProductDto>> getProduct(@PathVariable("id") final String id);

}
