package com.lambdasys.ecommerce.product.mapper;

import com.lambdasys.ecommerce.product.dto.ProductCategoryDto;
import com.lambdasys.ecommerce.product.model.ProductCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategoryDto toDto(ProductCategory entity);
    ProductCategory toEntity(ProductCategoryDto dto);

}
