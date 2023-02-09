package com.rrsys.productsapi.services;

import com.rrsys.productsapi.models.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.UUID;


public interface ProductService {
    Page<ProductsEntity> getAll(Pageable pageable);
    ProductsEntity get(UUID id);
    ProductsEntity create(ProductsEntity entity);
    ProductsEntity update(ProductsEntity entity);


}
