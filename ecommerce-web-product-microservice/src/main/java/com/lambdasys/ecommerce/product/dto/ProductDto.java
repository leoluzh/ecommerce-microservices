package com.lambdasys.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto implements Serializable {

    private String id;
    private String name;
    private String code;
    private String title;
    private String description;
    private String imageURL;
    private Double price;
    private String productCategoryName;

}
