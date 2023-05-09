package com.example.ordersystem.products_service.util;

import com.example.ordersystem.products_service.dto.ProductDto;
import com.example.ordersystem.products_service.entity.Product;
import org.springframework.beans.BeanUtils;

public class ProductDtoConverter {
    public static ProductDto toProductDto(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    public static Product toProduct(ProductDto dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return product;
    }
}
