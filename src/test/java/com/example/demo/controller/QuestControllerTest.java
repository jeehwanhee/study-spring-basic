package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restdocs.test.autoconfigure.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
public class QuestControllerTest {

    @Autowired
    MockMvc mockMvc;

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
}
