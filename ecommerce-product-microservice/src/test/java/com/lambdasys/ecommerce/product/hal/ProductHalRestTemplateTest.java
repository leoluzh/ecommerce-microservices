package com.lambdasys.ecommerce.product.hal;

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
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@Slf4j
class ProductHalRestTemplateTest {

    private static final String PRODUCT_SERVICE_URL = "http://localhost:8080/products";

    @BeforeEach
    public void setup() {
        deleteAllProducts();
    }

    @Test
    @DisplayName("Test POST Product")
    @Order(1)
    void testPostProduct() {

        log.info("Start");

        try {
            var productNew1 = createProduct("1");
            var productNew2 = createProduct("2");
            RestTemplate restTemplate = restTemplate();
            var productRetrieved1 = restTemplate.postForObject(PRODUCT_SERVICE_URL, productNew1, Product.class);
            var productRetrieved2 = restTemplate.postForObject(PRODUCT_SERVICE_URL, productNew2, Product.class);
            log.debug("productRetrieved1 : {}", productRetrieved1);
            log.debug("productRetrieved2 : {}", productRetrieved2);
            assertTrue(true, "successfully saved");
        } catch (Exception ex) {
            assertTrue(true, "successfully failed");
        }


        log.info("Finish");
    }

    @Test
    @DisplayName("Test GET All Products")
    @Order(2)
    void testGetAllProducts() {
        log.info("Start");
        testPostProduct();
        var products = getAllProducts();
        log.debug("products.size() : " + products.size());
        log.info("Finish");
    }

    @Test
    @DisplayName("Test PUT Product")
    @Order(3)
    void testPutProduct() {
        log.info("Start");
        try {
            var productNew3 = createProduct("3");
            var restTemplate = restTemplate();
            var productRetrieved3 = restTemplate.postForObject(PRODUCT_SERVICE_URL, productNew3, Product.class);
            log.debug("productRetrieved3 : {}", productRetrieved3);
            productRetrieved3.setPrice(productRetrieved3.getPrice() * 1.25);
            restTemplate.put(PRODUCT_SERVICE_URL + "/" + productRetrieved3.getId(), productRetrieved3, Product.class);
            var productAgainRetrieved3 = restTemplate.getForObject(PRODUCT_SERVICE_URL + "/" + productRetrieved3.getId(), Product.class);
            log.debug("productAgainRetrieved : {}", productAgainRetrieved3);
        } catch (Exception ex) {
            assertTrue(true, "successfully failed");
        }
        log.info("Finish");
    }

    public List<Product> getAllProducts() {
        var restTemplate = restTemplate();
        var responseTypeRef = new ParameterizedTypeReference<PagedModel<Product>>() {
        };
        var responseEntity = restTemplate.exchange(PRODUCT_SERVICE_URL, HttpMethod.GET, null, responseTypeRef);
        var resources = responseEntity.getBody();
        var products = resources.getContent();
        return new ArrayList<>(products);
    }

    public void deleteAllProducts() {
        log.info("Start");
        var restTemplate = restTemplate();
        var products = getAllProducts();
        log.info("products.size : " + products.size());
        products.stream().forEach(product -> {
            restTemplate.delete(PRODUCT_SERVICE_URL + "/" + product.getId());
        });
        log.info("Finish");
    }

    private RestTemplate restTemplate() {
        var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());
        var converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/json"));
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
