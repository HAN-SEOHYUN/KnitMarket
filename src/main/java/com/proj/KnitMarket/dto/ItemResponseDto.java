package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Member.Seller;
import lombok.Getter;

@Getter
public class ItemResponseDto {
    private Long id;
    private String itemName;
    private int price;
    private String itemDesc;
    private String sellerName;

    //entity=>dto
    public ItemResponseDto(Item entity){
        this.id=entity.getId();
        this.itemName=entity.getItemName();
        this.itemDesc=entity.getItemDesc();
        this.price=entity.getPrice();
        this.sellerName = entity.getSeller().getName();
    }

}
