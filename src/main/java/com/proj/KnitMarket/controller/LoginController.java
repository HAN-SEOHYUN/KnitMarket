package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.KakaoLoginService;
import com.proj.KnitMarket.Service.UserService;
import com.proj.KnitMarket.dto.UserRequestDto;
import com.proj.KnitMarket.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class LoginController {

    private final KakaoLoginService kakao;
    private final UserService userService;

    @GetMapping("/login")
    public String login_get() {

        log.info("loginController!!");

        return "user/login";
    }

    //http://localhost:8086/knitmarket/kakaoLogin/requestToken/user
    @RequestMapping(value = "/kakaoLogin/requestToken_user") // 일반회원로그인
    public String kakaoLoginRequestToken(@RequestParam("code") String code, Model model,
                                         HttpServletRequest request) {

        log.info("카카오로그인컨트롤러 code ={}", code);

        model.addAttribute("code", code);

        String role = "user";
        //accessToken 발급받기
        String access_Token = kakao.getAccessToken(code,role);
        log.info("controller access_token ={} ", access_Token);

        //user email과 name, kakaoId 받아오기
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        log.info("userInfo={}", userInfo);//여기까지 log에 찍힘

        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");
        String socialLoginKey = (String) userInfo.get("socialLoginKey");

        UserRequestDto userRequestDto;
        UserResponseDto userResponseDto = userService.findByEmail(email); // userDto 조회
        log.info("userResponseDto={}",userResponseDto);
        Long memberId;
        //db 중복 확인
        if (userResponseDto==null) { //신규가입
            userRequestDto = new UserRequestDto(email, name);
            memberId = userService.save(userRequestDto);

            log.info("신규회원가입 회원번호={}", memberId);

        }else{ //기존회원로그인
            memberId = userService.findByEmail(email).getId();
            log.info("기존회원 회원번호 ={}",memberId);
        }

        //세션저장
        HttpSession session=request.getSession();
        session.setAttribute("id", memberId);
        session.setAttribute("email", email);
        //session.setAttribute("access_Token",access_Token); //로그아웃때 필요한 accessToken
        session.setAttribute("name", name);

        return "/";
    }


}
