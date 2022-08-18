package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.dto.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class IndexController { //상품목록

    private final HttpSession httpSession;
    private final ItemService itemService;

    @GetMapping("/")
    public String index(Model model) {
        List<ItemResponseDto> itemDtoList = itemService.getItemList();
        model.addAttribute("itemList",itemDtoList);
        log.info("sellStatus={}",itemDtoList.toString());
        return "index";
    }

}


