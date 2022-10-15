package com.proj.KnitMarket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.proj.KnitMarket.Service.*;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.domain.Order.Cart;
import com.proj.KnitMarket.domain.Order.CartRepository;
import com.proj.KnitMarket.dto.CartDto;
import com.proj.KnitMarket.dto.SellerRequestDto;
import com.proj.KnitMarket.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Objects;
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
public class LoginController {

    private final KakaoLoginService kakao;
    private final NaverLoginService naver;
    private final UserService userService;
    private final SellerService sellerService;
    private final CartRepository cartRepository;

    //테스트용
    private final UserRepository userRepository;

    //로그인 창
    @GetMapping("/login")
    public String login_get() {

        log.info("로그인하기");

        return "user/login";
    }

    //카카오 판매자 회원 로그인
    @RequestMapping(value = "/kakaoLogin/requestToken_seller")
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
        String hp = (String) userInfo.get("phoneNumber");

        SellerRequestDto sellerRequestDto = null;
        Long sellerId;
        String url="/";
        String msg="";

        //db 중복 확인
        if (!sellerService.existsByEmail(email)) { //신규가입
            sellerRequestDto = new SellerRequestDto(email, name,hp);
            sellerId = sellerService.save(sellerRequestDto);

            log.info("신규회원가입 판매자번호={}", sellerId);
            msg = name+"님, 회원가입을 축하드립니다 ! ";

        }else{ //기존회원로그인
            sellerId = sellerService.findByEmail(email).getId();
            log.info("기존판매자 회원번호 ={}",sellerId);
            msg = name+"님, [판매자] 로그인되었습니다.";
        }

        //세션저장
        HttpSession session=request.getSession();
        session.setAttribute("id", sellerId);
        session.setAttribute("email", email);
        session.setAttribute("access_Token",access_Token); //로그아웃때 필요한 accessToken
        session.setAttribute("name", name);
        session.setAttribute("role",role);

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);

        return "common/message";
    }

    //카카오 일반 회원 로그인
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
        log.info("userInfo={}", userInfo);

        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");
        String socialLoginKey = (String) userInfo.get("socialLoginKey");
        String hp =(String) userInfo.get("phoneNumber");

        UserRequestDto userRequestDto = null;
        Long userId;
        String url="/";
        String msg = "";

        //db 중복 확인
        if (!userService.existsByEmail(email)) { //신규가입
            userRequestDto = new UserRequestDto(email, name, hp);
            User user = userService.save (userRequestDto);
            userId = user.getId();

            log.info("신규회원가입 회원번호={}", userId);

            //장바구니 생성
            CartDto createCartDto = CartDto.builder()
                    .user(user)
                    .build();

            Cart cart = cartRepository.save(createCartDto.toEntity());
            msg =name+"님, 회원가입을 축하드립니다 !";

        }else{ //기존회원로그인
            userId = userService.findByEmail(email).getId();
            log.info("기존회원 회원번호 ={}",userId);
            msg =name+"님, [사용자] 로그인되었습니다";
        }

        //세션저장
        HttpSession session=request.getSession();
        session.setAttribute("id", userId);
        session.setAttribute("email", email);
        session.setAttribute("access_Token",access_Token); //로그아웃때 필요한 accessToken
        session.setAttribute("name", name);
        session.setAttribute("role",role);



        if(name == "최지원"){
            msg = "서현친구 지원 방문 축하합니다 ^^ 까미사진보내줘.";
        }else if(name == "홍혜림"){
            msg ="서현친구 혜림 방문 축하합니다 ^^ 가지말라고 바짓가랑이 잡고싶은 심정";
        }else if(name == "최주은"){
            msg ="서현친구 주은 방문 축하합니다 ^^ 아기고양이 고딩주은 돌려주라주라 ..";
        }

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);

        return "common/message";
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

        String url = "/", msg ="로그아웃되었습니다";

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);

        return "common/message";
    }

    //세션끊기
    @GetMapping("/withdraw")
    public String userdeleteSocial_post(HttpSession session, HttpServletResponse response,
                                        Model model) {
        Long no=(Long)session.getAttribute("no");

        String access_Token = (String)session.getAttribute("access_Token");
        log.info("access_Token={}",access_Token);

        String msg="",url="";
        int cnt =0;

            if(access_Token!=null) {
                kakao.unlink(access_Token);
                log.info("카카오 세션 끊김");
                msg ="카카오 세션끊기 OK.";
                url ="/";

                session.invalidate();
            }else{
                msg ="먼저 로그인을 해주세요";
                url="/";
        }



        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        return "common/message";
    }

    //테스트 로그인
    @PostMapping("/testLogin")
    public String testLogin(@RequestParam ("testId")Long testId, @RequestParam("testPwd")String testPwd,HttpSession session,Model model){
        log.info("testId={}",testId);
        log.info("testPwd ={}",testPwd);
        User user = userRepository.findByIdAndEmail(testId, testPwd);

        String msg = "";
        String url = "/";

        if(user.getName()!=null || !Objects.equals(user.getName(), "")){
            msg = "테스트 로그인되었습니다";
            session.setAttribute("id", user.getId());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("name", user.getName());
            session.setAttribute("role","user");
            log.info("SUCCESS");
        }else{
            msg = "정보가 일치하지않습니다. 다시 로그인 해주십시오";
            log.info("FAIL");
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        return "common/message";
    }

    //네이버 판매자 로그인
    @RequestMapping("/naverLogin/requestToken_seller")
    public String authNaver(@RequestParam String code, @RequestParam String state,
                             Model model, HttpServletRequest request,
                            HttpServletResponse response)
            throws JsonProcessingException {
        String role = "seller";
        log.info("code : {}", code);

        String accessToken = naver.getAccessToken(code, state);
        log.info("accessToken : {}", accessToken);

        JsonNode json = naver.getNaverUserInfo(accessToken);
        log.info("json : {}", json);

        String id = json.get("id").asText();
        String name = json.get("name").asText();
        String email = json.get("email").asText();
        String hp = json.get("mobile").asText().replace("-", "");
        log.info("id={}, name={}, email={}, mobile={}", id, name, email, hp);

        SellerRequestDto sellerRequestDto = null;
        Long sellerId;
        String url="/";
        String msg="";

        //db 중복 확인
        if (!sellerService.existsByEmail(email)) { //신규가입
            sellerRequestDto = new SellerRequestDto(email, name,hp);
            sellerId = sellerService.save(sellerRequestDto);

            log.info("신규회원가입 판매자번호={}", sellerId);
            msg = name+"님, 회원가입을 축하드립니다 ! ";

        }else{ //기존회원로그인
            sellerId = sellerService.findByEmail(email).getId();
            log.info("기존판매자 회원번호 ={}",sellerId);
            msg = name+"님, [판매자] 로그인되었습니다.";
        }


        //세션저장
        HttpSession session=request.getSession();
        session.setAttribute("id", sellerId);
        session.setAttribute("email", email);
        session.setAttribute("access_Token",id); //로그아웃때 필요한 ID
        session.setAttribute("name", name);
        session.setAttribute("role",role);

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);

        return "common/message";
    }


    //네이버 사용자 로그인

}
