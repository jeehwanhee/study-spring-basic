package com.example.demo.service;

import com.example.demo.domain.Quest;
import com.example.demo.domain.QuestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestService {

    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public Quest createQuest(String title, String detail) {
        Quest quest = Quest.builder().title(title).detail(detail).build();

        return questRepository.save(quest);
    }

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public Quest getQuest(Long id) {
        return questRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 퀘스트를 찾을 수 없습니다."+id));
    }

    public void deleteQuest(Long id) {
        Quest quest = getQuest(id);
        questRepository.delete(quest);
    }

    @Transactional
    public Quest updateQuest(Long id, String title, String detail) {
        Quest quest = getQuest(id);

        quest.update(title, detail);

        return quest;
    }
}
