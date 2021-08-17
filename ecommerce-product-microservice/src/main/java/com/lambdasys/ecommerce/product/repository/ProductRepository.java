package com.lambdasys.ecommerce.product.repository;

import com.lambdasys.ecommerce.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByProductCategoryName(@Param("productCategory") String productCategoryName);
    List<Product> findByCode(@Param("code") String code);
}
