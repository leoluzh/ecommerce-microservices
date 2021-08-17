package com.lambdasys.ecommerce.product.client;

import com.lambdasys.ecommerce.product.dto.ProductDto;
import com.lambdasys.ecommerce.product.exception.ProductAlreadyExistException;
import com.lambdasys.ecommerce.product.exception.ProductNotFoundException;
import com.lambdasys.ecommerce.product.exception.ProductsNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductDto findById(final String id) throws ProductNotFoundException ;
    List<ProductDto> findAll();
    Page<ProductDto> findAll(final Pageable pageable);
    ProductDto save(final ProductDto productDto) throws ProductAlreadyExistException;
    ProductDto update(final String id, final ProductDto productDto) throws ProductNotFoundException;
    List<ProductDto> findByCode(String code);
    void delete(String id) throws ProductNotFoundException ;
    void deleteAll() throws ProductsNotFoundException;
}
