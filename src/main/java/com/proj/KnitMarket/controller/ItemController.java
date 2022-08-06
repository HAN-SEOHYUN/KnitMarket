package com.proj.KnitMarket.controller;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ItemController {

    private ItemService itemService;

    @PostMapping(value = "/register")
    public String save(@RequestBody ItemRequestDto itemDto) {
        log.info("아이템={}", itemDto.toString());

        Long itemId = itemService.save(itemDto);

        log.info("상품번호 ={}",itemId);
        return "redirect:/knitmarket";
    }
}
