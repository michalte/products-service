package com.example.ordersystem.products_service.controller;

import com.example.ordersystem.products_service.dto.ProductDto;
import com.example.ordersystem.products_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("all")
    public Flux<ProductDto> findAll() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> findById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound()
                        .build());
    }

    @GetMapping("range")
    public Flux<ProductDto> findProductsInRange(@RequestParam("min") int min,
                                                @RequestParam("max") int max) {
        return productService.getProductsWithinRange(min, max);
    }


    @PostMapping
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return productService.insertProduct(productDtoMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> productDtoMono) {
        return productService.updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound()
                        .build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteMapping(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}
