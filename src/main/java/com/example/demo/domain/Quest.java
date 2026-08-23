package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String detail;

    @Builder
    public Quest(String title, String detail) {
        validateTitle(title);
        validateDetail(detail);
        this.title = title;
        this.detail = detail;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("제목이 없음");
    }

    private void validateDetail(String detail) {
        if (detail == null || detail.length() < 2 || detail.length() > 100)
            throw new IllegalArgumentException("상세 내용은 2자 이상 100자 이하");
    }

    public void update(String title, String detail) {
        validateTitle(title);
        validateDetail(detail);
        this.title = title;
        this.detail = detail;
    }
}
