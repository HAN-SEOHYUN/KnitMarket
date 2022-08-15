package com.proj.KnitMarket.controller;
import com.proj.KnitMarket.Service.FileService;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.dto.FileRequestDto;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class ItemController {

    private final ItemService itemService;
    private final FileService fileService;

    @Value(("${image.path}"))
    private String uploadDir;


    @PostMapping(value = "/register")
    @ResponseBody
    public Long item_register_post(@ModelAttribute("item") ItemRequestDto itemDto, HttpSession httpSession) throws IOException {
        log.info("등록 아이템={}", itemDto.toString());
        String email = (String)httpSession.getAttribute("email");

        if(itemDto.getFile()!=null){
            log.info("이미지 有");
            MultipartFile file = itemDto.getFile();
            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            log.info("file.getOriginalFilenaem={}",file.getOriginalFilename());
            log.info("filePath={}",filePath);

            FileRequestDto fileDto = FileRequestDto.builder()
                    .orginFileName(file.getOriginalFilename())
                    .filePath(uploadDir+file.getOriginalFilename())
                    .build();
            long savedFileId = fileService.save(fileDto);
            log.info("savedFileId={}",savedFileId);
        }

        Long itemId = itemService.save(itemDto,email);

        log.info("등록된 상품번호 ={}",itemId);

        return itemId;
    }
}
