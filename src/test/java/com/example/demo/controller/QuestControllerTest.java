package com.example.demo.controller;

import com.example.demo.domain.Quest;
import com.example.demo.service.QuestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restdocs.test.autoconfigure.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
public class QuestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    QuestService questService;

    @Test
    @WithMockUser
    void createQuest() throws Exception {
        mockMvc.perform(post("/api/quest")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "테스트 퀘스트",
                            "detail": "테스트"
                        }"""
                ))
                .andExpect(status().isOk())
                .andDo(document("create-quest"));
    }

    @Test
    @WithMockUser
    void completeQuest() throws Exception {
        Quest quest = questService.createQuest("테스트 퀘스트", "테스트");

        MockMultipartFile file = new MockMultipartFile(
                "file", "proof.jpg", "image/jpeg", "test-image-content".getBytes());

        Long questId = quest.getId();
        mockMvc.perform(multipart(HttpMethod.PATCH,"/api/quests/{id}/complete", questId)
                .file(file))
                .andExpect(status().isOk())
                .andDo(document("complete-quest"));
    }
}
