package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.CartService;
import com.proj.KnitMarket.Service.OrderService;
import com.proj.KnitMarket.domain.Order.Order;
import com.proj.KnitMarket.domain.Order.OrderItem;
import com.proj.KnitMarket.dto.OrderDto;
import com.proj.KnitMarket.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    /*@GetMapping("/order/{orderId}")
    public String order_get(RedirectAttributes rttr) {
        Long orderId = (Long)rttr.getAttribute("orderId");
        log.info("requestParam orderId={}",orderId);
        return "user/order";
    }*/

    //단일상품주문
    @GetMapping("/order/{itemId}")
    public String order_item_get(@PathVariable("itemId")Long itemId, HttpSession httpSession, Model model){
        String email = (String) httpSession.getAttribute("email");
        List<OrderItemDto> orderItemDtoList = orderService.order(itemId, email);
        model.addAttribute("orderList",orderItemDtoList);
        return "user/order";
    }

    //장바구니상품주문
    @GetMapping("/order/cartItems")
    public String order_items_post(Model model,HttpSession httpSession){
        Long userId = (Long) httpSession.getAttribute("id");
        Long orderId = orderService.orders(userId);

        return "redirect:/knitmarket/order";
    }
}
