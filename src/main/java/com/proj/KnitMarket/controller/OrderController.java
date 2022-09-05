package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.CartService;
import com.proj.KnitMarket.Service.OrderService;
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
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    @GetMapping("/order")
    public String order_get() {

        log.info("주문하기");

        return "user/order";
    }

    //단일상품주문
    @GetMapping("/order/{itemId}")
    public String order_item_get(@PathVariable("itemId")Long itemId, HttpSession httpSession, Model model){

        String email = (String) httpSession.getAttribute("email");
        Long orderId = orderService.order(itemId, email);

        String url = "/knitmarket/", msg ="구매가 완료되었습니다";

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "/common/message";
    }

    //장바구니상품주문
    @GetMapping("/order/cartItems")
    public String order_items_post(Model model,HttpSession httpSession){
        Long userId = (Long) httpSession.getAttribute("id");

        int result = orderService.orders(userId);

        String url, msg;

        if(result==1){
            url="/knitmarket/cartlist";
            msg ="장바구니에 판매된 제품이 포함되어있습니다";
        }else{
            url="/knitmarket/";
            msg ="주문이 완료되었습니다";
        }
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "/common/message";
    }




}
