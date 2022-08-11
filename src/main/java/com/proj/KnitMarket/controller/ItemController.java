package com.proj.KnitMarket.controller;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping(value = "/knitmarket/register")
    @ResponseBody
    public Long save(@RequestBody ItemRequestDto itemDto, HttpSession httpSession) {
        log.info("등록 아이템={}", itemDto.toString());
        String email = (String)httpSession.getAttribute("email");

        Long itemId = itemService.save(itemDto,email);

        log.info("등록된 상품번호 ={}",itemId);

        return itemId;
    }
}
