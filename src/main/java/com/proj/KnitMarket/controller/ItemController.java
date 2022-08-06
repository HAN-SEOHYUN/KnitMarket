package com.proj.KnitMarket.controller;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class ItemController {

    private ItemService itemService;

    @GetMapping(value="/register")
    public String itemForm() {
        return "item/register";
    }

    @PostMapping(value = "/register")
    public String save(ItemRequestDto itemDto) {
        log.info("아이템={}", itemDto.toString());

        Long itemId = itemService.save(itemDto);

        log.info("상품번호 ={}",itemId);
        return "redirect:/knitmarket";
    }
}
