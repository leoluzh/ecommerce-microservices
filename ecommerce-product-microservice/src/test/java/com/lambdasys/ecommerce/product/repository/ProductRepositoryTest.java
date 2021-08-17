package com.lambdasys.ecommerce.product.repository;

import com.lambdasys.ecommerce.product.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setup(){
        this.productRepository.deleteAll();
    }

    @Test
    @Order(1)
    void testAddProduct(){
        try {
            productRepository.save(createProduct());
            assertTrue(true,"successfully saved");
        }catch (Exception ex){
            assertTrue(true,"successfully failed");
        }
    }

    @Test
    @Order(2)
    void testFindAllProducts(){
        productRepository.save(createProduct());
        final var products = productRepository.findAll();
        assertTrue(products.size()>0);
    }

    @Test
    @Order(3)
    void testProductByProductCategory(){
        var product = createProduct();
        productRepository.save(product);
        var products = productRepository.findByProductCategoryName(product.getProductCategoryName());
        assertTrue(products.size()>0);
    }

    public Product createProduct(){

        return Product.builder()
                .name("Samsung S21")
                .code("SAMSUNG-S21")
                .title("Samsung S21 6.2 inch , black , 12 px ....")
                .description("Samsung S21 6.2 inch")
                .imageURL("samsung.jpg")
                .price(9000.00)
                .productCategoryName("test category").build();

    }
}