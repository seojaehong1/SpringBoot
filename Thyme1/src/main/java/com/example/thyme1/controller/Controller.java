package com.example.thyme1.controller;


import com.example.thyme1.domain.Memo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("name", "타임리프");
        return "hello";
    }
    @GetMapping("/Users")
    public String users(Model model) {
        List<String> users = List.of("홍길동","김하나","서재홍");
        model.addAttribute("users", users);
        model.addAttribute("user", "admi");
        return "users";
    }
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("memo", new Memo());
        return "form";
    }

    @PostMapping("/form")
    public String handleForm(@ModelAttribute Memo memo) {
        System.out.println(memo.getTitle());
        return "result";
    }
}
