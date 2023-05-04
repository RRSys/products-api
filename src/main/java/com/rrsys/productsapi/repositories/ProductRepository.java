package com.rrsys.productsapi.repositories;

import com.rrsys.productsapi.models.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, UUID>{

    @Query(value="SELECT u FROM ProductsEntity u where u.name = ?1 OR ?1 is null")
    ProductsEntity findByName(@Param("name")String name);
}
