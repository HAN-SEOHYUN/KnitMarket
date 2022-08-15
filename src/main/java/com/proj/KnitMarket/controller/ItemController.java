package com.proj.KnitMarket.controller;
import com.proj.KnitMarket.Constant.ConstUtil;
import com.proj.KnitMarket.Service.FileService;
import com.proj.KnitMarket.Service.ItemService;
import com.proj.KnitMarket.dto.FileRequestDto;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    public String uploadDir= ConstUtil.UPLOAD_IMG_PATH_TEST;

    @GetMapping(value = "/register")
    public String item_register_get(Model model){
        model.addAttribute("item",new ItemRequestDto());
        return "item/register";
    }

    @PostMapping(value = "/register")
    public Long item_register_post(@ModelAttribute("item") ItemRequestDto itemDto, HttpSession httpSession) throws IOException{
        log.info("등록 아이템={}", itemDto.toString());
        log.info("이미지정보 ={}",itemDto.getFile().getOriginalFilename().toString());
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
            log.info("첨부이미지 id={}",savedFileId);
        }

        Long itemId = itemService.save(itemDto,email);

        log.info("등록된 상품번호 ={}",itemId);

        return itemId;
    }
}
