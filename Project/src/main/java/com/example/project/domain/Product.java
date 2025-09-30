package com.example.project.domain;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String productName;
    private int quantity;
}
