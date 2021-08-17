package com.lambdasys.ecommerce.product.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String id) {
        super(String.format("Product with id %s not found", id));
    }

}
