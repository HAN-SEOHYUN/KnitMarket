package com.proj.KnitMarket.controller;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class ItemController {

    private ItemService itemService;

    @Value("${image.path}")
    private String uploadDir;


    @GetMapping(value="/register")
    public String itemForm(ItemDto itemDto) {

        return "item/register";
    }

    @PostMapping(value = "/register")
    public String save(ItemDto itemDto) {
        Long itemId = itemService.save(itemDto);
        log.info("상품번호 ={}",itemId);
        return "redirect:/knitmarket";
    }
}
