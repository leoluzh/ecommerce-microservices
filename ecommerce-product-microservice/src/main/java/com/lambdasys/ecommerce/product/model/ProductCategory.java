package com.lambdasys.ecommerce.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("categories")
public class ProductCategory implements Serializable {

    @Id
    private String id;
    private String name;
    private String title;
    private String description;
    private String imageURL;

}
