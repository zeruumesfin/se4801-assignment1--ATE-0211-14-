// Student Number: ATE/0211/14
package com.shopwave.service;

import com.shopwave.dto.CreateProductRequest;
import com.shopwave.model.Product;
import com.shopwave.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    void shouldCreateProduct() {

        CreateProductRequest request = CreateProductRequest.builder()
                .name("Laptop")
                .description("Dell")
                .price(BigDecimal.valueOf(1000))
                .stock(5)
                .build();

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Laptop")
                .price(BigDecimal.valueOf(1000))
                .stock(5)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        var result = productService.createProduct(request);

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }


    @Test
    void shouldReturnProductById() {

        Product product = Product.builder()
                .id(1L)
                .name("Phone")
                .price(BigDecimal.valueOf(500))
                .stock(10)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        var result = productService.getProductById(1L);

        assertEquals("Phone", result.getName());
    }


    @Test
    void shouldThrowExceptionWhenProductNotFound() {

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            productService.getProductById(1L);
        });
    }
}