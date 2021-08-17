package com.lambdasys.ecommerce.product.repository;

import com.lambdasys.ecommerce.product.model.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {

}
