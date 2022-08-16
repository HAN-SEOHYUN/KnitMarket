package com.proj.KnitMarket.dto;

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

    //entity=>dto
    public ItemResponseDto(Item entity){
        this.id=entity.getId();
        this.itemName=entity.getItemName();
        this.itemDesc=entity.getItemDesc();
        this.price=entity.getPrice();
        this.sellerName = entity.getSeller().getName();
        this.orginFileName = entity.getFile().getOrginFileName();
    }

    @Builder
    public ItemResponseDto(Long id, String itemName, int price, String itemDesc, String sellerName, String orginFileName) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.itemDesc = itemDesc;
        this.sellerName = sellerName;
        this.orginFileName = orginFileName;
    }
}
