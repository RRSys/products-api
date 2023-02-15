package com.rrsys.productsapi.services.impl;

import com.rrsys.productsapi.models.ProductsEntity;
import com.rrsys.productsapi.repositories.ProductRepository;
import com.rrsys.productsapi.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    //injeção de dependencias
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public Page<ProductsEntity> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Override
    public ProductsEntity get(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public ProductsEntity create(ProductsEntity entity) {
        ProductsEntity product = repository.save(entity);
        return product;
    }

    @Override
    public ProductsEntity update(ProductsEntity entity) {
        return null;
    }
}
