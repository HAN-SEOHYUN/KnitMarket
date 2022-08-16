package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.dto.ItemListDto;
import com.proj.KnitMarket.dto.ItemSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class IndexController { //상품목록

    private final HttpSession httpSession;
    private final ItemService itemService;

    @GetMapping("/")
    public String index(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) { // @PathVariable : url 경로에 변수를 넣어줌
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,3);
        Page<ItemListDto> items = itemService.getIndexPage(itemSearchDto,pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage",5);
        return "index";
    }

}


