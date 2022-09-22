package com.proj.KnitMarket.controller;

import com.proj.KnitMarket.Constant.ConstUtil;
import com.proj.KnitMarket.Service.*;
import com.proj.KnitMarket.domain.Member.SellerRepository;
import com.proj.KnitMarket.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final FileService fileService;
    private final SellerService sellerService;
    private final AmazonS3Service amazonS3Service;

    public String uploadDir = ConstUtil.UPLOAD_IMG_PATH; //이미지 저장할 폴더

    //상품등록_get
    @GetMapping(value = "/register")
    public String item_register_get(Model model,HttpSession httpSession) {
        Long sellerId = (Long)httpSession.getAttribute("id");
        SellerResponseDto sellerResponseDto = sellerService.findById(sellerId);
        if(sellerResponseDto.getStore()==null || sellerResponseDto.getStore().isEmpty()){
            String url,msg;
            url = "/mypage/info";
            msg ="가게명을 먼저 등록해주세요";
            model.addAttribute("url",url);
            model.addAttribute("msg",msg);
            return "common/message";
        }

        model.addAttribute("item", new ItemRequestDto());
        model.addAttribute("seller", sellerResponseDto);
        return "Item/register";
    }

    //상품등록_img
    @PostMapping("/uploadImg")
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
       return amazonS3Service.upload(multipartFile,"uploadImg");
    }

    //상품등록_post
    @PostMapping(value = "/register")
    public String item_register_post(@ModelAttribute("item") ItemRequestDto itemDto, HttpSession httpSession, Model model, MultipartHttpServletRequest request) throws IOException {
        log.info("상품등록컨트롤러");
        String email = (String) httpSession.getAttribute("email");
        Long itemId = itemService.save(itemDto, email);

        String url = "/", msg ="상품등록이 완료되었습니다";

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "common/message";
    }

    //상품상세_get
    @GetMapping(value = "/detail/{id}")
    //http://localhost:8086/knitmarket/detail?id=28
    public String item_detail_get(Model model, @PathVariable("id") Long id) {
        ItemResponseDto itemResponseDto = itemService.getItemDetail(id);
        model.addAttribute("item", itemResponseDto);
        return "Item/detail";
    }

    //상품수정_get
    @GetMapping(value = "/register/{itemId}")
    public String item_update_get(Model model, @PathVariable(name = "itemId") Long itemId) {
        log.info("itemId ={}",itemId);
        ItemRequestDto itemRequestDto = itemService.getUpdateItem(itemId);
        SellerResponseDto sellerResponseDto = sellerService.findById(itemRequestDto.getSeller().getId());
        model.addAttribute("item", itemRequestDto);
        model.addAttribute("seller", sellerResponseDto);
        return "Item/register";
    }

    //상품수정_post
    @PostMapping(value = "/register/{itemId}")
    public String item_update_post(@ModelAttribute("item") ItemRequestDto itemRequestDto,Model model,@PathVariable(name="itemId")Long itemId) throws IOException {
        itemService.updateItem(itemId,itemRequestDto);
        String url = "/", msg ="수정이 완료되었습니다";

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "common/message";
    }

    //상품삭제_get
    @GetMapping(value = "/delete/{itemId}")
    public String item_delete_get(@PathVariable("itemId") Long id,HttpSession httpSession,Model model){
        Long sellerId = (Long) httpSession.getAttribute("id");
        ItemResponseDto itemResponseDto = itemService.getItemDetail(id);
        String url = "/", msg ="삭제가 완료되었습니다";

        if(sellerId == itemResponseDto.getSeller().getId()) { // 현재 로그인된 계정과 판매자 계정이 일치한다면
            log.info("deleteItem 메서드 실행 !");
            itemResponseDto = itemService.deleteItem(id);
        }else{
            msg = "삭제권한이 없습니다";
        }
        log.info("삭제여부={}",itemResponseDto.isDeleted());
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "common/message";
    }

}
