package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index() {

        return "index";
    }


    @GetMapping(value = "/register")
    public String item_register_get(@RequestBody ItemRequestDto item){
        return "item/register";
    }
}


