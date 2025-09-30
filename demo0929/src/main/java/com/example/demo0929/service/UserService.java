package com.example.demo0929.service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo0929.dto.JoinRequest;
import com.example.demo0929.dto.PasswordChangeRequest;
import com.example.demo0929.dto.UserDeleteRequest;
import com.example.demo0929.dto.UserUpdateRequest;
import com.example.demo0929.entity.User1;
import com.example.demo0929.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원 가입
     */
    public void join(JoinRequest req) {
        // 유효성 검사 (아이디, 이메일, 닉네임 중복 및 비밀번호 확인)
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        if (userRepository.existsByNickname(req.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }
        if (!req.getPassword().equals(req.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
        }

        // 비밀번호 암호화 및 User 객체 생성
        User1 newUser = new User1(
                req.getUsername(),
                passwordEncoder.encode(req.getPassword()),
                req.getEmail(),
                req.getNickname()
        );
        userRepository.save(newUser);
    }

    /**
     * 현재 로그인된 회원 정보 조회 (username으로 조회)
     */
    @Transactional(readOnly = true)
    public Optional<User1> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 회원 정보 수정 (이메일, 닉네임)
     */
    public void update(String username, UserUpdateRequest req) {
        User1 user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        if (req.getNewEmail() != null && !user.getEmail().equals(req.getNewEmail())) {
            if (userRepository.existsByEmail(req.getNewEmail())) {
                throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
            }
            user.setEmail(req.getNewEmail());
        }

        if (req.getNewNickname() != null && !user.getNickname().equals(req.getNewNickname())) {
            if (userRepository.existsByNickname(req.getNewNickname())) {
                throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
            }
            user.setNickname(req.getNewNickname());
        }
    }

    /**
     * 비밀번호 수정 (현재 비밀번호 인증 후 변경)
     */
    public void updatePassword(String username, PasswordChangeRequest req) {
        User1 user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        // 현재 비밀번호 확인
        if (!passwordEncoder.matches(req.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 새 비밀번호와 확인 일치 검증
        if (!req.getNewPassword().equals(req.getNewPasswordConfirm())) {
            throw new IllegalArgumentException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        // 비밀번호 암호화 및 업데이트
        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
    }

    /**
     * 회원 탈퇴
     */
    public void withdraw(String username, UserDeleteRequest req) {
        User1 user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        // 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        userRepository.delete(user);
    }
}