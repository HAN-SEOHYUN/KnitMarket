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

    //장바구니 목록
    @GetMapping("/cartlist")
    public String cart_list_get(Model model){
        Long userId = (Long)httpSession.getAttribute("id");
        List <CartItemDto> cartItemDtoList = cartService.getCartItemList(userId);
        model.addAttribute("cartList",cartItemDtoList);
        return "cart/list";
    }


    //장바구니 추가
    @GetMapping(value ="/cartAdd/{itemId}")
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

    //장바구니 삭제 (한개)
    @GetMapping(value = "/cartRemove/{cartItemId}")
    public String cart_remove_get(@PathVariable("cartItemId")Long cartItemId, Model model){
        log.info("cartRemoveController cartItemId={}",cartItemId);

        cartService.cartRemove(cartItemId);

        String url = "/knitmarket/cartlist", msg ="삭제되었습니다";
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "/common/message";
    }

    //장바구니 삭제 (여러개)
    @GetMapping(value ="/cartRemoveList/{cartItemIdList}")
    public String cart_remove_list_get(@PathVariable List<Long> cartItemIdList,Model model){
        log.info("cartRemoveList={}",cartItemIdList.size());
        for(Long cartItemId : cartItemIdList){
            cartService.cartRemove(cartItemId);
        }

        String url = "/knitmarket/cartlist", msg ="삭제되었습니다";
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return"/common/message";
    }


}
