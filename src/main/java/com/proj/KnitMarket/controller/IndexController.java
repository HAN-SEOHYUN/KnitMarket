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
public class IndexController {

    @GetMapping("/knitmarket")
    public String test() {

        return "index";
    }

}
