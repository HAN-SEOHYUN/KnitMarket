package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.CartService;
import com.proj.KnitMarket.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class CartController {

    private final CartService cartService;
    private final HttpSession httpSession;


    @GetMapping("/cartlist")
    public String cart_list_get(Model model){
        Long userId = (Long)httpSession.getAttribute("id");
        List <CartItemDto> cartItemDtoList = cartService.getCartItemList(userId);
        model.addAttribute("cartList",cartItemDtoList);
        return "cart/list";
    }


    //장바구니 추가
    @GetMapping(value ="/cart/{itemId}")
    public String cart_add_get(@PathVariable("itemId")Long itemId, HttpSession httpSession, Model model){
        log.info("CartController");
        Long userId = (Long) httpSession.getAttribute("id");
        String msg ="장바구니에 추가되었습니다", url ="/knitmarket/";
        if(userId==null){
            msg ="로그인 후 장바구니 이용이 가능합니다";
            url = "/knitmarket/login";
        }else{
            boolean addSuccess = cartService.save(userId, itemId);
            if(!addSuccess){
                msg ="장바구니에 이미 존재하는 상품입니다";
            }

        }
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "/common/message";
    }

}
