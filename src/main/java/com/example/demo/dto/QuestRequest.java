package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class QuestRequest {

    @NotBlank(message = "제목 필수")
    public String title;

    @Size(min=2, max=100, message = "상세 내용은 2자 이상 100자 이하")
    public String detail;
}
