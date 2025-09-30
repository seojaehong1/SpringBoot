package com.example.valid1.dto;

import com.example.valid1.entity.MyUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "이름은 반드시 입력해야 합니다.")
    private String name;

    @NotBlank(message = "이메일은 반드시 입력해야합니다.")
    @Email
    private String email;

    @Size(min = 6, max = 20, message="비밀번호는 6자 이상 20자 이하로 입력해야 합니다.")
    private String password;

    public MyUser toEntity() {
        return MyUser.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }

    public MyUser toEntity(String hashedPassword) {
        return  MyUser.builder()
                .name(name)
                .email(email)
                .password(hashedPassword)
                .build();
    }

}
