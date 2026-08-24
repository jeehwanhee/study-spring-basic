package com.example.demo.service;

import com.example.demo.domain.Quest;
import com.example.demo.domain.QuestRepository;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.QuestErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestService {

    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Transactional
    public Quest createQuest(String title, String detail) {
        Quest quest = Quest.builder().title(title).detail(detail).build();

        return questRepository.save(quest);
    }

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public Quest getQuest(Long id) {
        return questRepository.findById(id).orElseThrow(()->new BusinessException(QuestErrorCode.QUEST_NOT_FOUND));
    }

    @Transactional
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

    @Transactional
    public void completeQuest(Long id) {
        Quest quest = getQuest(id);
        quest.complete();
    }
}
