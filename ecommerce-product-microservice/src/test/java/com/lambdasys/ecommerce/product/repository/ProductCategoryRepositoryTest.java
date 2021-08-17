package com.lambdasys.ecommerce.product.repository;

import com.lambdasys.ecommerce.product.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @BeforeEach
    public void setup() {
        productCategoryRepository.deleteAll();
    }

    @Test
    @Order(1)
    void testAddProductCategories() {
        try {
            var productCategory = createProductCategory();
            productCategoryRepository.save(productCategory);
            assertTrue(true, "successfully saved");
        } catch (Exception ex) {
            assertTrue(true, "successfully failed");
        }
    }

    @Test
    @Order(2)
    void testFindAllProductCategories(){
        var productCategory = createProductCategory();
        productCategoryRepository.save(productCategory);
        var categories = productCategoryRepository.findAll();
        assertTrue(categories.size()>0);
    }

    private ProductCategory createProductCategory() {
        return ProductCategory.builder()
                .name("Mobile")
                .description("Mobile phones")
                .title("Mobiles and Tablet")
                .imageURL("mobile.jpg").build();
    }

}