package com.proj.KnitMarket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class MemberController {

    @GetMapping(value="/mypage")
    public String mypage_get(){
        log.info("내정보");
        return "user/mypage";
    }
}
