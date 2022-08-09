package com.proj.KnitMarket.controller;


import com.proj.KnitMarket.Service.KakaoLoginService;
import com.proj.KnitMarket.domain.Member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class LoginController {

    private final KakaoLoginService kakaoSerivce;
    private final Member member;

    @GetMapping("/login")
    public String login_get() {

        log.info("loginController!!");

        return "user/login";
    }


}
