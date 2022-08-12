package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.FileEntity;
import com.proj.KnitMarket.domain.Item.Item;
import lombok.Getter;

@Getter
public class ItemResponseDto {
    private Long id;
    private String itemName;
    private int price;
    private String itemDesc;
    private String sellerName;
    private FileEntity file;

    //entity=>dto
    public ItemResponseDto(Item entity){
        this.id=entity.getId();
        this.itemName=entity.getItemName();
        this.itemDesc=entity.getItemDesc();
        this.price=entity.getPrice();
        this.sellerName = entity.getSeller().getName();
        this.file = entity.getFile();
    }

}
