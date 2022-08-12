package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Member.Seller;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemRequestDto {
    private String itemName;
    private int price;
    private String itemDesc;
    private Seller seller;


    //private SellStatus sellStatus;

//dto => entity
    public Item toEntity() {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .itemDesc(itemDesc)
                .seller(seller)
                .build();
    }


    @Builder
    public ItemRequestDto(String itemName, String itemDesc, int price, Seller seller) {
        this.price = price;
        this.itemDesc = itemDesc;
        this.itemName = itemName;
        this.seller = seller;
    }

}
