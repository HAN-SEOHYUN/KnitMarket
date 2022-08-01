package com.proj.KnitMarket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/knitmarket")
    public String index() {

        log.info("indexController!!");

        return "index";
    }

}



