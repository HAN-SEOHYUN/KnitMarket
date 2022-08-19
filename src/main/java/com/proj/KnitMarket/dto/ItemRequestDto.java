package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.FileEntity;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Member.Seller;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemRequestDto {

    private Long id;
    private String itemName;
    private int price;
    private String itemDesc;
    private Seller seller;
    private MultipartFile file; //file
    private FileEntity fileEntity;
    private SellStatus sellStatus;

//dto => entity
    public Item toEntity() {
        return Item.builder()
                .id(id)
                .itemName(itemName)
                .price(price)
                .itemDesc(itemDesc)
                .seller(seller)
                .file(fileEntity)
                .sellStatus(sellStatus)
                .build();
    }

    @Builder
    public ItemRequestDto(String itemName, String itemDesc, int price, Seller seller, FileEntity file,SellStatus sellStatus,Long id) {
        this.id = id;
        this.price = price;
        this.itemDesc = itemDesc;
        this.itemName = itemName;
        this.seller = seller;
        this.fileEntity = file;
        this.sellStatus = sellStatus;
    }
}
