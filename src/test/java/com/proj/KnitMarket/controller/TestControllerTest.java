package com.proj.KnitMarket.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/post요청시 출력")
    void test()throws Exception {
        // expected
        mockMvc.perform(get("/")) //해당 url 로 요청함
                .andExpect(status().isOk()) // 응답 status 를 ok 로 테스트
                .andExpect(content().string("HELLO WORLD")) //content() ~로 내용등록
                .andDo(print()); //http 요청에 대한 summary 를 남겨주는 print 메서드
    }


}