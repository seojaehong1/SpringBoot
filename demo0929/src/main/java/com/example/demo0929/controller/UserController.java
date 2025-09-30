package com.example.demo0929.controller;
import com.example.demo0929.dto.JoinRequest;
import com.example.demo0929.dto.PasswordChangeRequest;
import com.example.demo0929.dto.UserDeleteRequest;
import com.example.demo0929.dto.UserUpdateRequest;
import com.example.demo0929.service.UserService;
import com.example.demo0929.entity.User1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원 가입
     */
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinRequest req) {
        try {
            userService.join(req);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 현재 로그인된 사용자 정보 조회
     * (임시로 username을 PathVariable로 받음)
     */
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserInfo(@PathVariable String username) {
        Optional<User1> userOptional = userService.findByUsername(username);
        if (userOptional.isPresent()) {
            User1 user = userOptional.get();
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("username", user.getUsername());
            userInfo.put("email", user.getEmail());
            userInfo.put("nickname", user.getNickname());
            // 비밀번호는 노출하지 않음
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }

    /**
     * 회원 정보 수정 (이메일, 닉네임)
     */
    @PutMapping("/{username}/update-profile")
    public ResponseEntity<String> updateProfile(@PathVariable String username, @RequestBody UserUpdateRequest req) {
        try {
            userService.update(username, req);
            return ResponseEntity.ok("회원 정보가 성공적으로 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 비밀번호 수정
     */
    @PutMapping("/{username}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable String username, @RequestBody PasswordChangeRequest req) {
        try {
            userService.updatePassword(username, req);
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<String> withdraw(@PathVariable String username, @RequestBody UserDeleteRequest req) {
        try {
            userService.withdraw(username, req);
            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
