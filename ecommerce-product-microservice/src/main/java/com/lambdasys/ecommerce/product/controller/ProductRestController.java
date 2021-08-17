package com.lambdasys.ecommerce.product.controller;

import com.lambdasys.ecommerce.product.dto.ProductDto;
import com.lambdasys.ecommerce.product.exception.ProductAlreadyExistException;
import com.lambdasys.ecommerce.product.exception.ProductNotFoundException;
import com.lambdasys.ecommerce.product.exception.ProductsNotFoundException;
import com.lambdasys.ecommerce.product.client.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ProductRestController implements ProductRestControllerDocs {

    private ProductService service;


    @Override
    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<ProductDto>> getProduct(@PathVariable("id") final String id) throws ProductNotFoundException {

        log.info("Fetching product with id: {}", id);

        final var product = service.findById(id);

        final var entityModel = EntityModel.of(
                product,
                linkTo(methodOn(ProductRestController.class).getProduct(product.getId())).withSelfRel()
        );

        return ResponseEntity.ok(entityModel);

    }

    @Override
    @PostMapping(produces = "application/hal+json")
    public ResponseEntity<EntityModel<ProductDto>> postProduct(@RequestBody @Valid final ProductDto productDto, UriComponentsBuilder ucBuilder) throws ProductAlreadyExistException, ProductNotFoundException {

        log.info("Creating product with code {}", productDto.getCode());

        final var product = service.save(productDto);

        final var headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/v1/products/{id}").buildAndExpand(product.getId()).toUri());

        final var entityModel = EntityModel.of(
                product,
                linkTo(methodOn(ProductRestController.class).getProduct(product.getId())).withSelfRel()
        );

        return new ResponseEntity<>(entityModel, headers, HttpStatus.OK);
    }

    @Override
    @PutMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<ProductDto>> updateProduct(@PathVariable("id") String id, @RequestBody @Valid ProductDto productDto) throws ProductNotFoundException {

        log.info("Updating product with id {}", id);

        final var product = this.service.update(id,productDto);

        final var entityModel = EntityModel.of(
                product,
                linkTo(methodOn(ProductRestController.class).getProduct(product.getId())).withSelfRel()
        );

        return ResponseEntity.ok(entityModel);
    }

    @Override
    @GetMapping(produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<ProductDto>>> getAllProducts() throws ProductsNotFoundException {

        final var products = service.findAll();
        final var linkSelf = linkTo(methodOn(ProductRestController.class).getAllProducts()).withSelfRel();
        final var linkGetAllProducts = linkTo(methodOn(ProductRestController.class).getAllProducts()).withRel("getAllProducts");

        if(CollectionUtils.isEmpty(products)){
           log.info("No products retrieved form repository");
           throw new ProductsNotFoundException();
        }

        final var models = products.stream().map(this::toEntityModel).toList();
        final var collectionModels = CollectionModel.of(models,linkSelf,linkGetAllProducts);

        return ResponseEntity.ok(collectionModels);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<ProductDto>> delete(@PathVariable("id") final String id) throws ProductNotFoundException {
        log.info("Fetching and deleting product with id {}",id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<EntityModel<ProductDto>> deleteAll() throws ProductsNotFoundException {
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private EntityModel<ProductDto> toEntityModel(ProductDto product){
        try {
            return EntityModel.of(product,
                    linkTo(methodOn(ProductRestController.class).getProduct(product.getId())).withSelfRel());
        }catch(ProductNotFoundException ex){
            return null;
        }
    }

}
