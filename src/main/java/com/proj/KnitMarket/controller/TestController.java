package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.dto.TestDto;
import com.proj.KnitMarket.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/")
    @ResponseBody
    public String test() {
        return "뜨개장터 테스트";
    }

    @GetMapping("/test")//localhost:8086
    public String TestWriteForm() {
        return "TestWrite";
    }


    @PostMapping("/test/write")
    public int TestWritePro(TestDto testDto) {
        log.info(testDto.getTitle());
        log.info(testDto.getContent());
        return testService.write(testDto);
    }

}
