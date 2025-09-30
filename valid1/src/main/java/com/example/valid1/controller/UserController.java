package com.example.valid1.controller;

import com.example.valid1.dto.UserRequest;
import com.example.valid1.entity.MyUser;
import com.example.valid1.repository.MyUserRepository;
import com.example.valid1.util.PasswordUtil;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    MyUserRepository myUserRepository;


    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@Valid @ModelAttribute("userRequest") UserRequest userRequest,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        model.addAttribute("message", "회원가입이 성공적으로 완료되었습니다.");
//        MyUser myUser = userRequest.toEntity();
        String hashedPassword = PasswordUtil.hashPassword(userRequest.getPassword());

        MyUser user = userRequest.toEntity(hashedPassword);
        myUserRepository.save(user);
        return "signup";
    }
}
