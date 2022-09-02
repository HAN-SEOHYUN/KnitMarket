package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.FileEntity;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Member.Seller;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemResponseDto {
    private Long id;
    private String itemName;
    private int price;
    private String itemDesc;
    private Seller seller;
    private String orginFileName;
    private SellStatus sellStatus;
    private LocalDateTime regTime; // 등록시간
    private MultipartFile file;
    private boolean isDeleted;

    //entity=>dto
    public ItemResponseDto(Item entity){
        this.id=entity.getId();
        this.itemName=entity.getItemName();
        this.itemDesc=entity.getItemDesc();
        this.price=entity.getPrice();
        this.seller = entity.getSeller();
        this.orginFileName = entity.getFile().getOrginFileName();
        this.sellStatus = entity.getSellStatus();
        this.regTime = entity.getRegTime();
        this.isDeleted = entity.isDeleted();
    }

    @Builder
    public ItemResponseDto(Long id, String itemName, int price, String itemDesc, String orginFileName,SellStatus sellStatus, LocalDateTime regTime, boolean isDeleted, Seller seller) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.itemDesc = itemDesc;
        this.orginFileName = orginFileName;
        this.sellStatus = sellStatus;
        this.regTime = regTime;
        this.isDeleted = isDeleted;
        this.seller = seller;
    }
}
