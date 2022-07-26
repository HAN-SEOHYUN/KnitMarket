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
    @DisplayName("test get 요청 시 출력")
    void test2()throws Exception{

        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("TestWrite"))
                .andDo(print());
    }

}