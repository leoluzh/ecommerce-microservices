package com.lambdasys.ecommerce.product.mapper;

import com.lambdasys.ecommerce.product.dto.ProductDto;
import com.lambdasys.ecommerce.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDto dto);

    ProductDto toDto(Product entity);

    void updateFromDto(ProductDto dto, @MappingTarget Product entity);

}
