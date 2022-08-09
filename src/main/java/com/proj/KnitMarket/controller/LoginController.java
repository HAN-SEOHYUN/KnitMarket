package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class LoginController {

    private final KakaoLoginService kakao;

    @GetMapping("/login")
    public String login_get() {

        log.info("loginController!!");

        return "user/login";
    }

    //http://localhost:8086/knitmarket/kakaoLogin/requestToken
    @RequestMapping(value = "/requestToken")
    public String kakaoLoginRequestToken(@RequestParam("code")String code, Model model,
                                         HttpServletRequest request) {

        log.info("카카오로그인컨트롤러 code ={}", code);
        model.addAttribute("code", code);

        //accessToken 발급받기
        String access_Token = kakao.getAccessToken(code);
        log.info("controller access_token ={} ", access_Token);

        return "/";
    }






}
