package com.example.jpa0924;

import com.example.jpa0924.entity.MyData;
import com.example.jpa0924.repository.MyDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Jpa0924ApplicationTests {

    @Autowired
    private MyDataRepository myDataRepository;


    @Test
    void contextLoads() {
        MyData myData = MyData.builder()
                .name("홍재서")
                .age(32)
                .email("wtme5@naver.com")
                .memo("실전입니다.")
                .build();
        myDataRepository.save(myData);
    }

    @Test
    void contextLoads_findById() {
        Optional<MyData> myData = myDataRepository.findById(1L);
        System.out.println(myData.get().getName());
    }

    @Test
    void contextLoads_findAll() {
        List<MyData> myData = myDataRepository.findAll();
        System.out.println(myData);
    }

}
