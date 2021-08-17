package com.lambdasys.ecommerce.product;

import com.lambdasys.ecommerce.product.model.Product;
import com.lambdasys.ecommerce.product.model.ProductCategory;
import com.lambdasys.ecommerce.product.repository.ProductCategoryRepository;
import com.lambdasys.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitializationComponent {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @PostConstruct
    public void init() {

        log.info("Start");

        final var productCategory =
                ProductCategory
                        .builder()
                        .name("Mobile")
                        .description("Mobile phones")
                        .title("Mobiles and Tablet")
                        .imageURL("mobile.jpg")
                        .build();

        productCategoryRepository.save(productCategory);

        final var product1 = Product.builder()
                .name("Samsung S21")
                .code("SAMSUNG-S21")
                .title("Samsung S21 6.2 inch , black , 12 px ....")
                .description("Samsung S21 6.2 inch")
                .imageURL("samsung.jpg")
                .price(9000.00)
                .productCategoryName(productCategory.getName()).build();

        productRepository.save(product1);

        final var product2 = Product.builder()
                .name("IPHONE 12")
                .code("IPHONE-12")
                .title("Apple Iphone 6.1 inch , white , 14px ....")
                .description("Apple Iphone 6.1 inch with NFC")
                .imageURL("iphone12.jpg")
                .price(12000.00)
                .productCategoryName(productCategory.getName())
                .build();

        productRepository.save(product2);

        log.info("Finish");

    }

}
