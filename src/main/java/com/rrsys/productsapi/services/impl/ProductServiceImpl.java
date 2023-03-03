package com.rrsys.productsapi.services.impl;

import com.rrsys.productsapi.models.ProductsEntity;
import com.rrsys.productsapi.repositories.ProductRepository;
import com.rrsys.productsapi.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
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
    public Optional<ProductsEntity> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public ProductsEntity create(ProductsEntity entity) {
        ProductsEntity product = repository.save(entity);
        return product;
    }

    @Override
    public ProductsEntity update(UUID id,ProductsEntity newValues) {
        //Buscando dado no banco
        ProductsEntity productDb = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        //Alterando novo dado
        productDb.setDescription(newValues.getDescription());
        productDb.setName(newValues.getName());

        //Verificação se o novo valor que for passado foi acima de 51%, é lançada uma exceçao
        double percent = productDb.getAmount() / 100 * 51;
        if (newValues.getAmount() > (percent + productDb.getAmount())) {
            throw new RuntimeException("Product not accept");
        } 
            productDb.setAmount(newValues.getAmount());
        

            //Salvando alterações
        return repository.save(productDb);



    }
    @Override
    public void delete(ProductsEntity entity) {
        repository.delete(entity);
    }
}
