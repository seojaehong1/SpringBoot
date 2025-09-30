package com.example.demo0929.dto;// JoinRequest.java
import lombok.Data;

@Data
public class JoinRequest {
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String nickname;
}

