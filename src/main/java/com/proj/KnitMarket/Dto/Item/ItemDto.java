package com.proj.KnitMarket.Dto.Item;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.User.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@Data
public class ItemDto {

    private Long id;
    private String itemName;
    private User seller;
    private int price;
    private String itemDesc;
    private int sellStatus;
    private LocalDate regTime;
    private Long fileId;

    public Item toEntity(){
        return Item.builder()
                .itemName(this.itemName)
                .seller(this.seller)
                .price(this.price)
                .itemDesc(this.itemDesc)
                .sellStatus(this.sellStatus)
                .regTime(this.regTime)
                .fileId(this.fileId)
                .build();
    }

    @Builder
    public ItemDto(String itemName, User seller, int price, String itemDesc, int sellStatus, LocalDate regTime, Long fileId) {
        this.itemName = itemName;
        this.seller = seller;
        this.price = price;
        this.itemDesc = itemDesc;
        this.sellStatus = sellStatus;
        this.regTime = regTime;
        this.fileId = fileId;
    }
}
