package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.UserService;
import com.proj.KnitMarket.dto.AddressDto;
import com.proj.KnitMarket.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class MemberController {

    private final UserService userService;

    @GetMapping(value="/mypage")
    public String mypage_get(Model model,HttpSession httpSession){
        Long userId = (Long)httpSession.getAttribute("id");
        UserResponseDto userResponseDto = userService.findById(userId);
        AddressDto addressDto = userService.getAddress(userId);
        model.addAttribute("address",addressDto);
        model.addAttribute("user",userResponseDto);
        return "user/mypage";
    }

    //주소등록
    @PostMapping(value = "/address")
    public String mypage_address_post(@ModelAttribute("") AddressDto addressDto,Model model,HttpSession httpSession) throws IOException {
        Long userId = (Long)httpSession.getAttribute("id");
        Long addressId = userService.save_address(addressDto,userId);
        log.info("addressId={}",addressId);

        String url = "/knitmarket/mypage", msg ="주소등록이 완료되었습니다";

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "/common/message";
    }

}
