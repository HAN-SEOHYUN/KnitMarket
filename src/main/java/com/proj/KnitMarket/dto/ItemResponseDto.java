package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemResponseDto {
    private Long id;
    private String itemName;
    private int price;
    private String itemDesc;
    private String sellerName;
    private String orginFileName;
    private SellStatus sellStatus; // 가져올땐 비교를 위해서 int 로

    //entity=>dto
    public ItemResponseDto(Item entity){
        this.id=entity.getId();
        this.itemName=entity.getItemName();
        this.itemDesc=entity.getItemDesc();
        this.price=entity.getPrice();
        this.sellerName = entity.getSeller().getName();
        this.orginFileName = entity.getFile().getOrginFileName();
        this.sellStatus = entity.getSellStatus();
    }

    @Builder
    public ItemResponseDto(Long id, String itemName, int price, String itemDesc, String sellerName, String orginFileName,SellStatus sellStatus) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.itemDesc = itemDesc;
        this.sellerName = sellerName;
        this.orginFileName = orginFileName;
        this.sellStatus = sellStatus;
    }
}
