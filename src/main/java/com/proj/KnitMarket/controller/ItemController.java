package com.proj.KnitMarket.controller;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class ItemController {

    private ItemService itemService;

    @Value("${image.path}")
    private String uploadDir;


    @GetMapping("/register")
    public String itemForm(Model model, ItemDto itemDto) {
        model.addAttribute("item",itemDto);
        return "item/register";
    }

    @PostMapping(value = "/register")
    public String itemAdd(@Valid ItemDto itemDto, BindingResult bindingResult,Model model) throws Exception {
        log.info("상품등록 컨트롤러!");

           long temp= itemService.save(itemDto);
           log.info("상품번호={}",temp);

        return "redirect:/register";
    }
}
