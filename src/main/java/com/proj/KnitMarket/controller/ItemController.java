package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Constant.ConstUtil;
import com.proj.KnitMarket.Service.FileService;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.domain.Item.FileEntity;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.dto.FileRequestDto;
import com.proj.KnitMarket.dto.FileResponseDto;
import com.proj.KnitMarket.dto.ItemRequestDto;
import com.proj.KnitMarket.dto.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
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


    @PostMapping(value = "/register")
    public String item_register_post(@ModelAttribute("item") ItemRequestDto itemDto, HttpSession httpSession, Model model) throws IOException {
        log.info("상품등록컨트롤러");
        String email = (String) httpSession.getAttribute("email");
        Long itemId = itemService.save(itemDto, email);

        return "/knitmarket/";
    }


    //상품상세
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

    @PostMapping(value = "/register/{itemId}")
    public String item_update_post(@ModelAttribute("item") ItemRequestDto itemRequestDto,Model model,@PathVariable(name="itemId")Long itemId) throws IOException {
        itemService.updateItem(itemId,itemRequestDto);
        return "/knitmarket/";

    }

}
