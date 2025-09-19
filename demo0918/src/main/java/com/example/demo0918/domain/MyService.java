package com.example.demo0918.domain;

import lombok.Data;

@Data
public class MyService {
    private Long id;
    private String name;         // 서비스명
    private String description;  // 설명
    private int price;           // 가격
}
