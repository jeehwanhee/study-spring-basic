package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "아이디를 입력하세요.")
    public String username;

    @NotBlank(message = "비밀번호를 입력하세요.")
    public String password;

}
