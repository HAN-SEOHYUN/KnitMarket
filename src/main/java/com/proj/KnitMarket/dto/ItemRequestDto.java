package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.Item;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
public class ItemRequestDto {
    private String itemName;
    private int price;
    private String itemDesc;

    //private SellStatus sellStatus;

    public Item toEntity(){
        Item item = Item.builder()
                .itemName(itemName)
                .price(price)
                .itemDesc(itemDesc)
                .build();
        return item;
    }

    @Builder
    public ItemRequestDto(String itemName,String itemDesc,int price){
        this.price=price;
        this.itemDesc = itemDesc;
        this.itemName=itemName;
    }

}
