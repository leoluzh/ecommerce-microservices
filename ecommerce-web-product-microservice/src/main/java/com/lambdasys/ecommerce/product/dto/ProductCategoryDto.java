package com.lambdasys.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDto implements Serializable {

    private String id;
    private String name;
    private String title;
    private String description;
    private String imageURL;

}
