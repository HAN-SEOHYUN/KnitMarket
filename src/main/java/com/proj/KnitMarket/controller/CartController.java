package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.CartService;
import com.proj.KnitMarket.domain.Order.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class CartController {

    private final CartService cartService;

    //AJAX로 들어올꺼임
    //장바구니 추가
    @GetMapping(value ="/cart/{itemId}")
    public String cart_add_get(@PathVariable("itemId")Long itemId, HttpSession httpSession, Model model){
        Long userId = (Long) httpSession.getAttribute("id");

        log.info("CartController");

        Cart cart = cartService.save(userId, itemId);

        String msg ="장바구니에 추가되었습니다", url ="/knitmarket/";
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "/common/message";
    }

}
