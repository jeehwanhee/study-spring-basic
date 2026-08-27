package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restdocs.test.autoconfigure.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Test
    void signup() throws Exception {
        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"username":"testSignup",
                        "password":"test1!"}
                        """))
                .andExpect(status().isOk())
                .andDo(document("signup"));
    }

    @Test
    void loginSuccess() throws Exception {
        userService.signup("testLogin", "test1!");

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"username":"testLogin",
                        "password":"test1!"}
                        """))
                .andExpect(status().isOk())
                .andDo(document("login-success"));
    }

    @Test
    void loginFail_wrongPassword() throws Exception {
        userService.signup("testLogin", "test1!");

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"username":"testLogin",
                        "password":"test1!wrong"}
                        """))
                .andExpect(status().isUnauthorized())
                .andDo(document("login-fail"));
    }

}
