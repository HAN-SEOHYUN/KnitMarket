package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/")
    public String index() {

        return "index";
    }

}


