package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Constant.ConstUtil;
import com.proj.KnitMarket.Service.FileService;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.Service.SellerService;
import com.proj.KnitMarket.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class ItemController {

    private final ItemService itemService;
    private final FileService fileService;

    public String uploadDir = ConstUtil.UPLOAD_IMG_PATH_TEST; //이미지 저장할 폴더

    //상품등록_get
    @GetMapping(value = "/register")
    public String item_register_get(Model model) {
        model.addAttribute("item", new ItemRequestDto());
        return "item/register";
    }

    //상품등록_post
    @PostMapping(value = "/register")
    public String item_register_post(@ModelAttribute("item") ItemRequestDto itemDto, HttpSession httpSession, Model model) throws IOException {
        log.info("상품등록컨트롤러");
        String email = (String) httpSession.getAttribute("email");
        Long itemId = itemService.save(itemDto, email);

        return "/knitmarket/";
    }

    //상품상세_get
    @GetMapping(value = "/detail/{id}")
    //http://localhost:8086/knitmarket/detail?id=28
    public String item_detail_get(Model model, @PathVariable("id") Long id) {
        ItemResponseDto itemResponseDto = itemService.getItemDetail(id);
        model.addAttribute("item", itemResponseDto);
        return "item/detail";
    }

    //상품수정_get
    @GetMapping(value = "/register/{itemId}")
    public String item_update_get(Model model, @PathVariable(name = "itemId") Long itemId) {
        log.info("itemId ={}",itemId);
        ItemRequestDto itemRequestDto = itemService.getUpdateItem(itemId);
        model.addAttribute("item", itemRequestDto);
        return "item/register";
    }

    //상품수정_post
    @PostMapping(value = "/register/{itemId}")
    public String item_update_post(@ModelAttribute("item") ItemRequestDto itemRequestDto,Model model,@PathVariable(name="itemId")Long itemId) throws IOException {
        itemService.updateItem(itemId,itemRequestDto);
        return "index";
    }

    //상품삭제_get
    @GetMapping(value = "/delete/{itemId}")
    public String item_delete_get(@PathVariable("itemId") Long id,HttpSession httpSession){
        Long sellerId = (Long) httpSession.getAttribute("id");
        ItemResponseDto itemResponseDto = itemService.getItemDetail(id);

        if(sellerId == itemResponseDto.getSellerId()){ // 현재 로그인된 계정과 판매자 계정이 일치한다면
           itemResponseDto= itemService.deleteItem(id);
        }
        log.info("삭제여부={}",itemResponseDto.isDeleted());
        return "index";
    }

}
