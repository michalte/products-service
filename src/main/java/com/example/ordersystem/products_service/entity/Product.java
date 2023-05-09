package com.example.ordersystem.products_service.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@ToString
public class Product {
    @Id
    private String id;
    private String description;
    private Integer price;
}
