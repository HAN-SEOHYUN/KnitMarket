package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.KakaoLoginService;
import com.proj.KnitMarket.Service.SellerService;
import com.proj.KnitMarket.Service.UserService;
import com.proj.KnitMarket.dto.SellerRequestDto;
import com.proj.KnitMarket.dto.UserRequestDto;
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
/*
        session.setAttribute("id", userId);
        session.setAttribute("email", email);
        session.setAttribute("access_Token",access_Token); //로그아웃때 필요한 accessToken
        session.setAttribute("name", name);
        session.setAttribute("role",role);
* */


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class LoginController {

    private final KakaoLoginService kakao;
    private final UserService userService;
    private final SellerService sellerService;

    @GetMapping("/login")
    public String login_get() {

        log.info("로그인하기");

        return "user/login";
    }

    //http://localhost:8086/knitmarket/kakaoLogin/requestToken_seller
    @RequestMapping(value = "/kakaoLogin/requestToken_seller") // 판매자 회원 로그인
    public String kakaoLoginRequestToken_seller(@RequestParam("code") String code, Model model,
                                         HttpServletRequest request) {
        String role = "seller";
        log.info("카카오로그인={}",role,", access_code={}", code);
        //accessToken 발급받기
        String access_Token = kakao.getAccessToken(code,role);
        log.info("controller access_token ={} ", access_Token);

        //user email과 name, kakaoId 받아오기
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        log.info("userInfo={}", userInfo);//여기까지 log에 찍힘

        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");
        String socialLoginKey = (String) userInfo.get("socialLoginKey");

        SellerRequestDto sellerRequestDto = null;
        Long sellerId;
        String url="";
        String msg="";

        //db 중복 확인
        if (!sellerService.existsByEmail(email)) { //신규가입
            sellerRequestDto = new SellerRequestDto(email, name);
            sellerId = sellerService.save(sellerRequestDto);

            log.info("신규회원가입 판매자번호={}", sellerId);

        }else{ //기존회원로그인
            sellerId = sellerService.findByEmail(email).getId();
            log.info("기존판매자 회원번호 ={}",sellerId);
        }

        //세션저장
        HttpSession session=request.getSession();
        session.setAttribute("id", sellerId);
        session.setAttribute("email", email);
        session.setAttribute("access_Token",access_Token); //로그아웃때 필요한 accessToken
        session.setAttribute("name", name);
        session.setAttribute("role",role);

        model.addAttribute("url","/knitmarket/");
        model.addAttribute("msg","로그인 되었습니다");

        return "/common/message";
    }

    @RequestMapping(value = "/kakaoLogin/requestToken_user") // 일반회원로그인
    public String kakaoLoginRequestToken_user(@RequestParam("code") String code, Model model,
                                              HttpServletRequest request) {
        String role = "user";
        log.info("카카오로그인={}",role,", access_code={}", code);
        //accessToken 발급받기
        String access_Token = kakao.getAccessToken(code,role);
        log.info("controller access_token ={} ", access_Token);

        //user email과 name, kakaoId 받아오기
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        log.info("userInfo={}", userInfo);//여기까지 log에 찍힘

        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");
        String socialLoginKey = (String) userInfo.get("socialLoginKey");

        UserRequestDto userRequestDto = null;
        Long userId;
        String url="";
        String msg="";

        //db 중복 확인
        if (!userService.existsByEmail(email)) { //신규가입
            userRequestDto = new UserRequestDto(email, name);
            userId = userService.save(userRequestDto);

            log.info("신규회원가입 회원번호={}", userId);

        }else{ //기존회원로그인
            userId = userService.findByEmail(email).getId();
            log.info("기존회원 회원번호 ={}",userId);
        }

        //세션저장
        HttpSession session=request.getSession();
        session.setAttribute("id", userId);
        session.setAttribute("email", email);
        session.setAttribute("access_Token",access_Token); //로그아웃때 필요한 accessToken
        session.setAttribute("name", name);
        session.setAttribute("role",role);

        model.addAttribute("url","/knitmarket/");
        model.addAttribute("msg","성공");

        return "/common/message";
    }

    //로그아웃
    @RequestMapping(value="/logout")
    public String logout(HttpSession session, Model model) {
        kakao.kakaoLogout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("id");
        session.removeAttribute("email");
        session.removeAttribute("name");
        session.removeAttribute("role");

        String url = "/knitmarket/", msg ="로그아웃되었습니다";

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);

        return "/common/message";
    }




}
