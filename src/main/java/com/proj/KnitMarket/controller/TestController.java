package com.proj.KnitMarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

   /*@GetMapping("/")
    @ResponseBody
    public String test(){
        return "뜨개장터 테스트";
    }*/

    @GetMapping("/test")//localhost:8086
    public String TestWriteForm(){

       return "TestWrite";
    }
}
