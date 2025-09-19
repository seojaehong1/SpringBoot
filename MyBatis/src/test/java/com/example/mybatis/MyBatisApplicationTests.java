package com.example.mybatis;

import com.example.mybatis.dao.BoardMapper;
import com.example.mybatis.model.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyBatisApplicationTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    void contextLoads() {
        List<Board> boards = boardMapper.findAll();
        for (Board board : boards) {
            System.out.println(board);
        }
    }

}
