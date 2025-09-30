package com.example.home0925;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.home0925.mapper")
public class Home0925Application {

    public static void main(String[] args) {
        SpringApplication.run(Home0925Application.class, args);
    }
    }
