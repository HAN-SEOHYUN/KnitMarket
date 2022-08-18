package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.Item;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemResponseDto {
    private Long id;
    private String itemName;
    private int price;
    private String itemDesc;
    private String sellerName;
    private Long sellerId;
    private String orginFileName;
    private SellStatus sellStatus;
    private LocalDateTime regTime; // 등록시간


    //entity=>dto
    public ItemResponseDto(Item entity){
        this.id=entity.getId();
        this.itemName=entity.getItemName();
        this.itemDesc=entity.getItemDesc();
        this.price=entity.getPrice();
        this.sellerName = entity.getSeller().getName();
        this.sellerId = entity.getSeller().getId();
        this.orginFileName = entity.getFile().getOrginFileName();
        this.sellStatus = entity.getSellStatus();
        this.regTime = entity.getRegTime();
    }

    @Builder
    public ItemResponseDto(Long id, String itemName, int price, String itemDesc, String sellerName, String orginFileName,SellStatus sellStatus, LocalDateTime regTime,Long sellerId) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.itemDesc = itemDesc;
        this.sellerName = sellerName;
        this.orginFileName = orginFileName;
        this.sellStatus = sellStatus;
        this.regTime = regTime;
        this.sellerId = sellerId;
    }
}
