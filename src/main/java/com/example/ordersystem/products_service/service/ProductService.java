package com.example.ordersystem.products_service.service;

import com.example.ordersystem.products_service.dto.ProductDto;
import com.example.ordersystem.products_service.repository.ProductRepository;
import com.example.ordersystem.products_service.util.ProductDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .map(ProductDtoConverter::toProductDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return productRepository.findById(id)
                .map(ProductDtoConverter::toProductDto);
    }

    public Flux<ProductDto> getProductsWithinRange(int min, int max) {
        return productRepository.findByPriceBetween(Range.closed(min, max))
                .map(ProductDtoConverter::toProductDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(ProductDtoConverter::toProduct)
                .flatMap(productRepository::insert)
                .map(ProductDtoConverter::toProductDto);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono) {
        return productRepository.findById(id)
                .flatMap(product -> productDtoMono
                        .map(ProductDtoConverter::toProduct)
                        .doOnNext(p -> p.setId(id)))
                .flatMap(productRepository::save)
                .map(ProductDtoConverter::toProductDto);
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }

}
