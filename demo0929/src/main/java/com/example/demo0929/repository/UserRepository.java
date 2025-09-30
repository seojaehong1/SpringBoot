package com.example.demo0929.repository;


import com.example.demo0929.entity.User1;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User1, Long> {

    // 아이디 중복 검사를 위한 메서드
    boolean existsByUsername(String username);

    // 이메일 중복 검사를 위한 메서드
    boolean existsByEmail(String email);

    // 닉네임 중복 검사를 위한 메서드
    boolean existsByNickname(String nickname);

    // 아이디로 회원 정보를 조회하기 위한 메서드
    Optional<User1> findByUsername(String username);
}