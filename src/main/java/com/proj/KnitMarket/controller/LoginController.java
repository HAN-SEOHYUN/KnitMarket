package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.KakaoLoginService;
import com.proj.KnitMarket.Service.MemberService;
import com.proj.KnitMarket.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class LoginController {

    private final KakaoLoginService kakao;
    private final MemberService memberService;

    @GetMapping("/login")
    public String login_get() {

        log.info("loginController!!");

        return "user/login";
    }

    //http://localhost:8086/knitmarket/kakaoLogin/requestToken
    @RequestMapping(value = "/kakaoLogin/requestToken")
    public String kakaoLoginRequestToken(@RequestParam("code")String code, Model model,
                                         HttpServletRequest request) {

        log.info("카카오로그인컨트롤러 code ={}", code);
        model.addAttribute("code", code);

        //accessToken 발급받기
        String access_Token = kakao.getAccessToken(code);
        log.info("controller access_token ={} ", access_Token);

        //user email과 name, kakaoId 받아오기
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        log.info("userInfo={}",userInfo);

        String name = (String)userInfo.get("name");
        String email = (String)userInfo.get("email");
        String socialLoginKey = (String)userInfo.get("socialLoginKey");

        MemberRequestDto memberRequestDto = new MemberRequestDto(email, name);

        Long memberId = memberService.save(memberRequestDto);

        log.info("회원가입회원번호 ={}",memberId);

        return "/";
    }






}
