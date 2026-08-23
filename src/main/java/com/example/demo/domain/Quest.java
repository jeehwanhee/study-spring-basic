package com.example.demo.domain;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.QuestErrorCode;
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
    private boolean completed;

    @Builder
    public Quest(String title, String detail) {
        validateTitle(title);
        validateDetail(detail);
        this.title = title;
        this.detail = detail;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank())
            throw new BusinessException(QuestErrorCode.INVALID_TITLE);
    }

    private void validateDetail(String detail) {
        if (detail == null || detail.length() < 2 || detail.length() > 100)
            throw new BusinessException(QuestErrorCode.INVALID_DETAIL);
    }

    public void update(String title, String detail) {
        validateTitle(title);
        validateDetail(detail);
        this.title = title;
        this.detail = detail;
    }

    public void complete() {
        if (completed)
            throw new BusinessException(QuestErrorCode.ALREADY_COMPLETED);
        completed = true;
    }
}
