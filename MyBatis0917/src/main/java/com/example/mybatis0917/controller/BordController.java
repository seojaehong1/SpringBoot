package com.example.mybatis0917.controller;

import com.example.mybatis0917.dao.BoardMapper;
import com.example.mybatis0917.model.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BordController {

    private final BoardMapper boardMapper;

    public BordController(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @GetMapping
    String showBoard(@RequestParam(required = false) Integer editId, Model model) {
        Board editTarget = (editId != null) ? boardMapper.findById(editId) : new Board();
        List<Board> list = boardMapper.findAll();
        model.addAttribute("list", list);
        model.addAttribute("form", editTarget);
        return "board";
    }

    @PostMapping("/save")
    String save(Board board) {
        if (board.getId() == null) {
            boardMapper.insert(board);
        } else {
            boardMapper.update(board);
        }
        return "redirect:/board";
    }

    @GetMapping("/delete/{id}")
    String delete(@PathVariable Integer id) {
        boardMapper.delete(id);
        return "redirect:/board";
    }
}
