package com.proj.KnitMarket.controller;
import com.proj.KnitMarket.Dto.File.FileDto;
import com.proj.KnitMarket.Dto.Item.ItemDto;
import com.proj.KnitMarket.Dto.Item.ItemRequest;
import com.proj.KnitMarket.Service.FileService;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.domain.User.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class ItemController {

    private FileDto fileDto;
    private FileService fileService;
    private ItemDto itemDto;
    private ItemService itemService;

    @Value("${image.path}")
    private String uploadDir;


    @GetMapping("/register")
    public String homeView(Model model) {
        model.addAttribute("item", new ItemRequest());
        return "item/register";
    }

    @PostMapping("/register")
    public String saveFormRequests(@ModelAttribute("item") ItemRequest itemRequest) throws IOException {
        String itemName = itemRequest.getItemName();
        int price = itemRequest.getPrice();
        String itemDesc = itemRequest.getItemDesc();


        ItemDto itemDto = ItemDto.builder()
                .itemName(itemName)
                .price(price)
                .itemDesc(itemDesc)
                .build();

        if (itemRequest.getFile() != null) {
            MultipartFile file = itemRequest.getFile();
            String fullPath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(fullPath));
            log.info("file.getOriginalFilename = {}", file.getOriginalFilename());
            log.info("fullPath = {}", fullPath);

            FileDto fileDto = FileDto.builder()
                    .originFileName(file.getOriginalFilename())
                    .fullPath(uploadDir + file.getOriginalFilename())
                    .build();
            Long savedFileId = fileService.save(fileDto);
            itemDto.setFileId(savedFileId);
        }
        itemService.save(itemDto);

        return "redirect:/knitmarket/register";
    }


}
