package com.lambdasys.ecommerce.product.controller;

import com.lambdasys.ecommerce.product.dto.ProductDto;
import com.lambdasys.ecommerce.product.exception.ProductAlreadyExistException;
import com.lambdasys.ecommerce.product.exception.ProductNotFoundException;
import com.lambdasys.ecommerce.product.exception.ProductsNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Product API" , description = "Manage products")
public interface ProductRestControllerDocs {


    @Operation(description = "Returns product found by a given id")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Success product found in the system.",
                    content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "404", description = "Product with given id not found.", content = @Content)
    })
    ResponseEntity<EntityModel<ProductDto>> getProduct(final String id) throws ProductNotFoundException;


    @Operation(description = "Product creating operation")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Success product creation.",
                    content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Missing required fields or wrong field range value.", content = @Content)
    })
    ResponseEntity<EntityModel<ProductDto>> postProduct(final ProductDto productDto, @Parameter(hidden = true) UriComponentsBuilder ucBuilder) throws ProductAlreadyExistException , ProductNotFoundException;

    @Operation(description = "Update person by a given id and with request body")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Success product found in the system and updated.",
                    content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "404", description = "Product with given id not found.", content = @Content)
    })
    ResponseEntity<EntityModel<ProductDto>> updateProduct(final String id, final ProductDto productDto) throws ProductNotFoundException;


    @Operation(description = "Return a list of all products registered in the system.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200" , description = "List of all products registered in the system."),
            @ApiResponse(responseCode = "404", description = "No products retrieved from repository.", content = @Content)
    })
    ResponseEntity<CollectionModel<EntityModel<ProductDto>>> getAllProducts() throws ProductsNotFoundException;


    @Operation(description="Delete an product found by a given valid id")
    @ApiResponses(value={
            @ApiResponse(responseCode = "204", description = "Success product deleted in the system." , content = @Content(mediaType = "application/json")) ,
            @ApiResponse(responseCode = "404", description = "Product with given id not found.", content = @Content)
    })
    ResponseEntity<EntityModel<ProductDto>> delete(final String id) throws ProductNotFoundException;


    @Operation(description="Delete all products")
    @ApiResponses(value={
            @ApiResponse(responseCode = "204", description = "Success deleted all products in the system." , content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<EntityModel<ProductDto>> deleteAll() throws ProductsNotFoundException;

}
