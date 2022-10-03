package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.SellerService;
import com.proj.KnitMarket.Service.UserService;
import com.proj.KnitMarket.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class MemberController {

    private final UserService userService;
    private final SellerService sellerService;

    //내 정보 조회
    @GetMapping(value="/info")
    public String mypage_get(Model model,HttpSession httpSession){
        String role = (String)httpSession.getAttribute("role");
        Long memberId = (Long)httpSession.getAttribute("id");

        if(role=="user"){
            UserResponseDto userResponseDto = userService.findById(memberId);
            AddressDto addressDto = userService.getAddress(memberId);

            model.addAttribute("member",userResponseDto);
            model.addAttribute("address",addressDto);

        }else if(role=="seller") {
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

        String url = "/mypage/info", msg ="주소등록이 완료되었습니다";

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "common/message";
    }

    //주소수정
    @PostMapping(value = "/address/{addressId}")
    public String address_update_post(@ModelAttribute("address") AddressDto addressDto ,
                                      Model model, @PathVariable(name="addressId")Long addressId){
        userService.updateAddress(addressId,addressDto);
        String url ="/mypage/info", msg="주소수정이 완료되었습니다";
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return"common/message";
    }

    //가게명 등록 및 수정
    @PostMapping(value="/store/{sellerId}")
    public String store_save_post(@RequestParam("store")String store, Model model, @PathVariable(name ="sellerId")Long sellerId,
                                  @RequestParam("accountBank") String accountBank,@RequestParam("accountNum") String accountNum, @RequestParam("accountName")String accountName){
        sellerService.updateStore(sellerId, store,accountBank, accountNum,accountName);

        String url ="/mypage/info", msg="판매자 정보가 업데이트 되었습니다";
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "common/message";
    }


    //사용자 주문목록
    @GetMapping("/orderList/{userId}")
    public String orderList_get(@PathVariable("userId")Long userId,Model model){
        List<OrderDto> orderDtoList = userService.selectOrderList(userId);
        model.addAttribute("orderList",orderDtoList);
        return "user/orderList";
    }

    //내 상품 (판매자가 게시한 상품 목록 조회)
    @GetMapping(value = "/myItem/{sellerId}")
    public String myItem_list_get(@PathVariable ("sellerId")Long sellerId, Model model,HttpSession httpSession){
        List<ItemResponseDto> itemResponseDtoList = sellerService.getMyItemList(sellerId);
        model.addAttribute("myItemList",itemResponseDtoList);
        return "seller/myItem";
    }

    //배송정보 조회
    @GetMapping(value = "/orderInfo/{orderId}")
    public String orderInfo_get(@PathVariable ("orderId") Long orderId, Model model){
    OrderDto orderDto = sellerService.getOrderInfo(orderId);
    model.addAttribute("order", orderDto);

        return "seller/orderInfo";
    }

}
