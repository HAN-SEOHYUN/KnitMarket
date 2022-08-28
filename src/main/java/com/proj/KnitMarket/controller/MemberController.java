package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.SellerService;
import com.proj.KnitMarket.Service.UserService;
import com.proj.KnitMarket.dto.AddressDto;
import com.proj.KnitMarket.dto.SellerResponseDto;
import com.proj.KnitMarket.dto.UserResponseDto;
import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class MemberController {

    private final UserService userService;
    private final SellerService sellerService;

    @GetMapping(value="/mypage")
    public String mypage_get(Model model,HttpSession httpSession){
        String role = (String)httpSession.getAttribute("role");
        Long memberId = (Long)httpSession.getAttribute("id");
        if(role=="user"){
            UserResponseDto userResponseDto = userService.findById(memberId);
            AddressDto addressDto = userService.getAddress(memberId);

            model.addAttribute("address",addressDto);
            model.addAttribute("member",userResponseDto);
        }else {
            SellerResponseDto sellerResponseDto = sellerService.findById(memberId);
            model.addAttribute("member",sellerResponseDto);
        }
        return "user/mypage";
    }

    //주소등록
    @PostMapping(value = "/address")
    public String mypage_address_post(@ModelAttribute("address") AddressDto addressDto,Model model,HttpSession httpSession) throws IOException {
        Long userId = (Long)httpSession.getAttribute("id");
        Long addressId = userService.save_address(addressDto,userId);
        log.info("저장된 addressId={}",addressId);

        String url = "/knitmarket/mypage", msg ="주소등록이 완료되었습니다";

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "/common/message";
    }

    //주소수정
    @PostMapping(value = "/address/{addressId}")
    public String address_update_post(@ModelAttribute("address") AddressDto addressDto ,
                                      Model model, @PathVariable(name="addressId")Long addressId){
        log.info("수정 예정 addressId={}",addressId);
        userService.updateAddress(addressId,addressDto);
        String url ="/knitmarket/mypage", msg="주소수정이 완료되었습니다";
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return"/common/message";
    }

    //가게명 수정
    @PostMapping(value="/store/{sellerId}")
    public String store_save_post(@RequestParam("store")String store, Model model, @PathVariable(name ="sellerId")Long sellerId){
        log.info("store={}",store);
        log.info("sellerId={}",sellerId);
        sellerService.updateStore(sellerId, store);

        String url ="/knitmarket/mypage", msg="가게명이 업데이트 되었습니다";
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "/common/message";
    }
}
