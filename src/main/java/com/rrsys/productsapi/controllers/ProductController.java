package com.rrsys.productsapi.controllers;

import com.rrsys.productsapi.Dtos.ProductDto;
import com.rrsys.productsapi.models.ProductsEntity;
import com.rrsys.productsapi.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto productDto){
        ProductsEntity productsEntity = new ProductsEntity();
        BeanUtils.copyProperties(productDto, productsEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(productsEntity));
    }
}