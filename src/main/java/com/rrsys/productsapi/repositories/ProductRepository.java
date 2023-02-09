package com.rrsys.productsapi.repositories;

import com.rrsys.productsapi.models.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, UUID>{
}
