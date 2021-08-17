package com.lambdasys.ecommerce.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductsNotFoundException extends Exception {

    public ProductsNotFoundException() {
        super("No products retrieved from repository");
    }

}
