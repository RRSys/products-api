package com.rrsys.productsapi.services;

import com.rrsys.productsapi.models.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.awt.print.Pageable;
=======
import org.springframework.data.domain.Pageable;
>>>>>>> eca4936 (Create findAll)
import java.util.UUID;


public interface ProductService {
    Page<ProductsEntity> getAll(Pageable pageable);
    ProductsEntity get(UUID id);
    ProductsEntity create(ProductsEntity entity);
    ProductsEntity update(ProductsEntity entity);


}
