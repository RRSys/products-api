package com.rrsys.productsapi.services;

import com.rrsys.productsapi.models.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;


public interface ProductService {
    Page<ProductsEntity> getAll(Pageable pageable);
    Optional<ProductsEntity> getById(UUID id);
    ProductsEntity create(ProductsEntity entity);
    ProductsEntity update(ProductsEntity entity);


}
