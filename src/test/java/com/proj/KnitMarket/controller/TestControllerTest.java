package com.proj.KnitMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.KnitMarket.domain.repository.TestRepository;
import com.proj.KnitMarket.dto.TestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.security.RunAs;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */

@WebMvcTest
class TestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestRepository testRepository;

    @BeforeEach
    void clean() {
        testRepository.deleteAll();
    }

    @Test
    @DisplayName("test get 요청 시 출력")
    void test2() throws Exception {

        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("TestWrite"))
                .andDo(print());
    }

    @Test
    @DisplayName("글작성 post 테스트")
    void test3() throws Exception {

        //given
        TestDto testDto = TestDto.builder()
                .title("타이틀")
                .content("내용")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(testDto);


        //expected

        mockMvc.perform(post("/test/write")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());



    }


}