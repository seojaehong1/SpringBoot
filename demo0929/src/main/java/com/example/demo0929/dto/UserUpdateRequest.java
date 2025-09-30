package com.example.demo0929.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String newEmail;
    private String newNickname;
}