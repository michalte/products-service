package com.example.ordersystem.products_service.service;

import com.example.ordersystem.products_service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class DataSetupService implements CommandLineRunner {

    private final ProductService productService;

    @Override
    public void run(String... args) {
        ProductDto product1 = new ProductDto("4k-tv", 800);
        ProductDto product2 = new ProductDto("iphone-11", 700);
        ProductDto product3 = new ProductDto("headphones", 100);
        ProductDto product4 = new ProductDto("calculator", 20);

        Flux.just(product1, product2, product3, product4)
                .flatMap(product -> productService.insertProduct(Mono.just(product)))
                .subscribe(System.out::println);
    }
}
