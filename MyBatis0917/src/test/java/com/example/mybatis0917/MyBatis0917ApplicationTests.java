package com.example.mybatis0917;

import com.example.mybatis0917.dao.BoardMapper;
import com.example.mybatis0917.model.Board;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setExtractBareNamePropertyMethods;

//@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE) //테스트에서 데이터베이스 설정이 무시되지 않도록 막아주는 설정
class MyBatis0917ApplicationTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    void test1() {
        List<Board> boards = boardMapper.findAll();
        for (Board board : boards) {
            System.out.println(board);
        }
    }

    @Test
    void test2(){

        //given
        Board board = new Board();
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");

        //when
        boardMapper.insert(board);
        List<Board> boards = boardMapper.findAll();
        Board saved = boards.get(0);
//        System.out.println(saved);

        //then
        assertThat(saved.getTitle()).isEqualTo("테스트 제목");
        assertThat(saved.getContent()).isEqualTo("테스트 내용");
    }
    
    @Test
    void test3(){
        
        Board board = new Board();
        board.setTitle("원래 내용");
        board.setContent("원래 내용");
        boardMapper.insert(board);
        
        Board saved = boardMapper.findAll().get(0);
        saved.setTitle("수정된 제목");
        saved.setContent("수정된 내용");
        
        boardMapper.update(saved);
        Board updated = boardMapper.findById(saved.getId());
        
        assertThat(updated.getTitle()).isEqualTo(saved.getTitle());
        assertThat(updated.getContent()).isEqualTo(saved.getContent());
        
    }
    @Test
    void test4(){

        Board board = new Board();
        board.setTitle("제목");
        board.setContent("내용");
        boardMapper.insert(board);

        Board saved = boardMapper.findAll().get(0);
        boardMapper.delete(saved.getId());

        Board result = boardMapper.findById(saved.getId());
        assertThat(result).isNull();
    }
}
