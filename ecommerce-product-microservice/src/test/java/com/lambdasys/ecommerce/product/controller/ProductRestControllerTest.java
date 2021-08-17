package com.lambdasys.ecommerce.product.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdasys.ecommerce.product.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@Slf4j
class ProductRestControllerTest {

    private static final String PRODUCT_SERVICE_URL = "http://localhost:8080/api/v1/products";

    @BeforeEach
    public void setup() {

    }

    @DisplayName("Test POST Product")
    @Test
    @Order(1)
    void testPostProduct() {
        log.info("Start test post product");
        var productNew1 = createProduct("1");
        var restTemplate = restTemplate();
        var productRetreived1 = restTemplate.postForObject(PRODUCT_SERVICE_URL, productNew1, Product.class);
        log.info("Product with product id {} created", productRetreived1.getId());
        assertNotNull(productRetreived1.getId(), "Create a product");
        log.info("End test post product");
    }

    @DisplayName("Test GET a product")
    @Test
    @Order(2)
    void testGetAProduct() {
        log.info("Start test get a product");
        final var productNew2 = createProduct("2");
        final var restTemplate = restTemplate();
        final var productCreated2 = restTemplate.postForObject(PRODUCT_SERVICE_URL, productNew2, Product.class);
        log.info("Product with product id {} created", productNew2.getId());
        final var productRetrieved2 = restTemplate.getForObject((PRODUCT_SERVICE_URL + "/" + productCreated2.getId()), Product.class);
        log.info("Product with product id {} retrieved", productRetrieved2.getId());
        assertNotNull(productRetrieved2.getId(), "Retrieved a product");
    }

    @DisplayName("Test PUT product")
    @Test
    @Order(3)
    void testPutProduct() {
        log.info("Start test put a product");
        final var productNew3 = createProduct("3");
        final var restTemplate = restTemplate();
        final var productCreated3 = restTemplate.postForObject(PRODUCT_SERVICE_URL, productNew3, Product.class);
        log.info("Product with product id {} created", productCreated3.getId());
        productCreated3.setPrice(productCreated3.getPrice() * 1.25);
        restTemplate.put(PRODUCT_SERVICE_URL + "/" + productCreated3.getId(), productCreated3, Product.class);
        var productAgainRetrieved3 = restTemplate.getForObject(PRODUCT_SERVICE_URL + "/" + productCreated3.getId(), Product.class);
        assertNotNull(productAgainRetrieved3, "Retrieved a product");
    }

    @DisplayName("Test DELETE a product")
    @Test
    @Order(4)
    void testDeleteAProduct() {
        log.info("Start test delete a product");
        final var productNew4 = createProduct("4");
        final var restTemplate = restTemplate();
        final var productCreated4 = restTemplate.postForObject(PRODUCT_SERVICE_URL, productNew4, Product.class);
        log.info("Product with product id {} created", productCreated4.getId());
        restTemplate.delete(PRODUCT_SERVICE_URL + "/" + productCreated4.getId());
        final var productAgainRetrieved4 = restTemplate.getForObject(PRODUCT_SERVICE_URL + "/" + productCreated4.getId(), Product.class);
        assertNull(productAgainRetrieved4, "Product not found because it was delete");
    }

    @DisplayName("Test DELETE all products")
    @Test
    @Order(5)
    void testDeleteAllProducts() {
        log.info("Start test delete all products");
        final var restTemplate = restTemplate();
        final var productNew5 = createProduct("5");
        final var productCreated5 = restTemplate.postForObject(PRODUCT_SERVICE_URL, productNew5, Product.class);
        log.info("Product with product id {} created", productCreated5.getId());
        final var productNew6 = createProduct("6");
        final var productCreated6 = restTemplate.postForObject(PRODUCT_SERVICE_URL, productNew6, Product.class);
        log.info("Product with product id {} created", productCreated6.getId());
        restTemplate.delete(PRODUCT_SERVICE_URL);
        final var productAgainRetrieved5 = restTemplate.getForObject(PRODUCT_SERVICE_URL + "/" + productCreated5.getId(), Product.class);
        assertNull(productAgainRetrieved5, "Product not found because it was delete");
        final var productAgainRetrieved6 = restTemplate.getForObject(PRODUCT_SERVICE_URL + "/" + productCreated6.getId(), Product.class);
        assertNull(productAgainRetrieved6, "Product not found because it was delete");
    }

    private List<Product> getAllProducts() {
        final var restTemplate = restTemplate();
        final var responseTypeRef = new ParameterizedTypeReference<PagedModel<Product>>() {
        };
        final var responseEntity = restTemplate.exchange(PRODUCT_SERVICE_URL, HttpMethod.GET, null, responseTypeRef);
        final var resources = responseEntity.getBody();
        final var products = resources.getContent();
        return new ArrayList<>(products);
    }

    private RestTemplate restTemplate() {
        final var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());
        final var converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json, application/json"));
        converter.setObjectMapper(mapper);
        return new RestTemplate(Arrays.asList(converter));
    }

    private Product createProduct(String id) {
        return Product.builder()
                .name("Samsung S21" + "-" + id)
                .code("SAMSUNG-S21" + "-" + id)
                .title("Samsung S21 6.2 inch , black , 12 px ...." + "-" + id)
                .description("Samsung S21 6.2 inch" + "-" + id)
                .imageURL("samsung.jpg")
                .price(9000.00)
                .productCategoryName("test category").build();
    }

}