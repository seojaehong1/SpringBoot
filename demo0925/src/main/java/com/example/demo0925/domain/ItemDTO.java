package com.example.demo0925.domain;

import lombok.Data;

@Data // Lombok 어노테이션으로 Getter, Setter, equals, hashCode, toString 자동 생성
public class ItemDTO {
    private int itemId; // item_id (INT, AUTO_INCREMENT, PK)
    private String title; // title (VARCHAR)
    private String brand; // brand (VARCHAR)
    private String description; // description (TEXT)
    private int price; // price (INT)
}