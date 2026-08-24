package com.example.demo;

import com.example.demo.domain.Quest;
import com.example.demo.domain.QuestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Profile("test")
@Component
public class DummyDataInit implements CommandLineRunner {

    private final QuestRepository questRepository;

    public DummyDataInit(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<List<String>> quests = Arrays.asList(
                Arrays.asList("아침 7시 기상하기", "일어나서 물 한 잔 마시기"),
                Arrays.asList("백준 알고리즘 풀기", "오늘의 Solved.ac 추천 문제 1개 풀기"),
                Arrays.asList("스프링 복습하기", "데이터 수정(PUT) API 만드는 법 찾아보기")
        );

        for (int i=0; i<3; i++) {
            Quest q = Quest.builder()
                    .title(quests.get(i).get(0)).detail(quests.get(i).get(1)).build();
            questRepository.save(q);
        }

    }
}
