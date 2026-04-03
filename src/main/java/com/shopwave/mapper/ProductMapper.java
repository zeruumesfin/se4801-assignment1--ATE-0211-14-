// Student Number: ATE/0211/14
package com.shopwave.mapper;

import com.shopwave.dto.ProductDTO;
import com.shopwave.model.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryId(
                        product.getCategory() != null ? product.getCategory().getId() : null
                )
                .build();
    }
}