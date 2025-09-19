package com.example.mybatis.controller;

import com.example.mybatis.dao.BoardMapper;
import com.example.mybatis.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private BoardMapper boardMapper;

    @GetMapping("/")
    public String index(){
        List<Board> boards = boardMapper.findAll();
        for (Board board : boards) {
            System.out.println(board);
        }

        return "index";
    }
}
