package com.example.demo;

import com.example.demo.domain.Quest;
import com.example.demo.domain.QuestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class DummyDataInit implements CommandLineRunner {

    private final QuestRepository questRepository;

    public DummyDataInit(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Quest q1 = new Quest();
        q1.setTitle("아침 7시 기상하기");
        q1.setDetail("일어나서 물 한 잔 마시기");
        questRepository.save(q1);

        Quest q2 = new Quest();
        q2.setTitle("백준 알고리즘 풀기");
        q2.setDetail("오늘의 Solved.ac 추천 문제 1개 풀기");
        questRepository.save(q2);

        Quest q3 = new Quest();
        q3.setTitle("스프링 복습하기");
        q3.setDetail("데이터 수정(PUT) API 만드는 법 찾아보기");
        questRepository.save(q3);
    }
}
