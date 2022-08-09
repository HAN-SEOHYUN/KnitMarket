package com.proj.KnitMarket.controller;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping(value = "/knitmarket/register")
    @ResponseBody
    public Long save(@RequestBody ItemRequestDto itemDto) {
        log.info("아이템={}", itemDto.toString());

        Long itemId = itemService.save(itemDto);

        log.info("상품번호 ={}",itemId);

        return itemId;
    }
}
