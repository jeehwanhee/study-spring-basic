package com.example.demo.dto;

import com.example.demo.domain.Quest;

public class QuestResponse {
    public Long id;
    public String title;
    public String detail;

    public QuestResponse(Quest quest) {
        this.id = quest.getId();
        this.title = quest.getTitle();
        this.detail = quest.getDetail();
    }
}
