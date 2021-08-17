package com.lambdasys.ecommerce.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyExistException extends Exception {

    public ProductAlreadyExistException(String code){
        super(String.format("A product with code %s already exist",code));
    }

}
