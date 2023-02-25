package com.rrsys.productsapi.controllers;

import com.rrsys.productsapi.Dtos.ProductDto;
import com.rrsys.productsapi.models.ProductsEntity;
import com.rrsys.productsapi.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

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
    @GetMapping
    public ResponseEntity<Object> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
            return ResponseEntity.status(HttpStatus.OK).body(service.getAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        Optional<ProductsEntity> productsEntityOptional = service.getById(id);
        return productsEntityOptional.<ResponseEntity<Object>>map(productsEntity -> ResponseEntity.status(HttpStatus.OK).
                body(productsEntity)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Product not found"));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id){
        Optional<ProductsEntity> productsOptional = service.getById(id);
        if (!productsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        service.delete(productsOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable UUID id,@RequestBody @Valid ProductDto productDto){
        ProductsEntity productsEntity = new ProductsEntity();
        BeanUtils.copyProperties(productDto, productsEntity);
        service.update(id,productsEntity);
        return ResponseEntity.noContent().build();

    }



}
