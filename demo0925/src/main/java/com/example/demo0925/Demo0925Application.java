package com.example.demo0925;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 매퍼 인터페이스가 위치한 패키지를 정확히 지정합니다.
@MapperScan(basePackages = "com.example.demo0925.mapper")
public class Demo0925Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo0925Application.class, args);
    }
}

