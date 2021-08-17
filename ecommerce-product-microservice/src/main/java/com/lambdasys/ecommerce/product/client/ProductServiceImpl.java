package com.lambdasys.ecommerce.product.client;

import com.lambdasys.ecommerce.product.dto.ProductDto;
import com.lambdasys.ecommerce.product.exception.ProductAlreadyExistException;
import com.lambdasys.ecommerce.product.exception.ProductNotFoundException;
import com.lambdasys.ecommerce.product.exception.ProductsNotFoundException;
import com.lambdasys.ecommerce.product.mapper.ProductMapper;
import com.lambdasys.ecommerce.product.model.Product;
import com.lambdasys.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ProductDto findById(final String id) throws ProductNotFoundException {
        var product = verifyIfExists(id);
        return mapper.toDto(product);
    }

    @Override
    public List<ProductDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Page<ProductDto> findAll(final Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::toDto);
    }

    @Override
    public ProductDto save(ProductDto productDto) throws ProductAlreadyExistException {

        final var products = findByCode(productDto.getCode());

        if (!CollectionUtils.isEmpty(products)) {
            log.info("A product with code {} already exist.", productDto.getCode());
            throw new ProductAlreadyExistException(productDto.getCode());
        }

        final var resultado = this.repository.save(mapper.toEntity(productDto));
        return mapper.toDto(resultado);
    }

    @Override
    public ProductDto update(String id, ProductDto productDto) throws ProductNotFoundException {
        final var entity = verifyIfExists(id);
        mapper.updateFromDto(productDto, entity);
        return mapper.toDto(this.repository.save(entity));
    }

    @Override
    public List<ProductDto> findByCode(String code) {
        return this.repository
                .findByCode(code)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(String id) throws ProductNotFoundException {
        final var entity = verifyIfExists(id);
        this.repository.delete(entity);
    }

    @Override
    public void deleteAll() throws ProductsNotFoundException {
        var count = repository.count();
        if (count == 0) {
            throw new ProductsNotFoundException();
        }
        log.info("Deleting {} products", count);
        repository.deleteAll();
    }

    public Product verifyIfExists(final String id) throws ProductNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

}
