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

    public String uploadDir = ConstUtil.UPLOAD_IMG_PATH_TEST; //이미지 저장할 폴더

    @GetMapping(value = "/register")
    public String item_register_get(Model model) {
        model.addAttribute("item", new ItemRequestDto());
        return "item/register";
    }

    @PostMapping(value = "/register")
    public String item_register_post(@ModelAttribute("item") ItemRequestDto itemDto, HttpSession httpSession, Model model) throws IOException {
        log.info("ItemRequestDto={}", itemDto.toString());
        String email = (String) httpSession.getAttribute("email");

        String url = "";
        String msg = "";

        if (itemDto.getFile() != null) {
            log.info("이미지 有");
            MultipartFile file = itemDto.getFile();
            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            log.info("file.getOriginalFilename={}", file.getOriginalFilename());
            log.info("filePath={}", filePath);

            FileRequestDto fileDto = FileRequestDto.builder()
                    .orginFileName(file.getOriginalFilename())
                    .filePath(uploadDir + file.getOriginalFilename())
                    .build();

            Long itemId = itemService.save(itemDto, email, fileDto);

            log.info("상품번호 ={}", itemId);

            url = "/knitmarket/";
            msg = "상품등록이 완료되었습니다";
        } else {
            url = "/knitmarket/";
            msg = "상품등록에 실패했습니다 상품 정보를 확인해주세요 ! ";

        }
        model.addAttribute("url", url);
        model.addAttribute("msg", msg);
        return "/common/message";
    }


    //상품상세
    @GetMapping(value = "/detail")
    public String item_detail_get() {

        return "item/detail";
    }





}
