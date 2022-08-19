package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.FileEntity;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Member.Seller;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ItemUpdateDto {

    private String itemName;
    private int price;
    private String itemDesc;
    private Seller seller;
    private MultipartFile file;
    private FileEntity fileEntity;
    private SellStatus sellStatus;



}
