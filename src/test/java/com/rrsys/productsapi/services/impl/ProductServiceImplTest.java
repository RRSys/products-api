package com.rrsys.productsapi.services.impl;

import com.rrsys.productsapi.models.ProductsEntity;
import com.rrsys.productsapi.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void testUpdateSuccess() {
        //Mock de Id do produto
        UUID id = UUID.randomUUID();
        ProductsEntity productDb = new ProductsEntity(100);
        //Mock do retorno do banco de dados
        when(productRepository.findById(id)).thenReturn(Optional.of(productDb));
        when(productRepository.save(productDb)).thenReturn(productDb);

        //Chamada do metodo update da service
        ProductsEntity result = productService.update(id, new ProductsEntity(150));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(150D,result.getAmount());
    }

    @Test
    void testUpdateFail() {
        ProductsEntity productDb = new ProductsEntity(100);
        //Mock do retorno do banco de dados
        when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(productDb));

        Exception exception = assertThrows(RuntimeException.class, ()-> {
            productService.update(UUID.randomUUID(), new ProductsEntity(151));
        });
        assertTrue(exception.getMessage().contains("Product not accept"));

    }
}