package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.FileEntity;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Member.Seller;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemRequestDto {

    @NotBlank(message = "상품명은 필수 입력 값입니다")
    private String itemName;

    @NotNull(message = "가격은 필수 입력 값입니다")
    private int price;

    private String itemDesc;
    private Seller seller;

    @NotNull(message = "상품이미지는 필수 입력 값입니다")
    private MultipartFile file;
    private FileEntity fileEntity;

    private SellStatus sellStatus;

//dto => entity
    public Item toEntity() {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .itemDesc(itemDesc)
                .seller(seller)
                .file(fileEntity)
                .sellStatus(sellStatus)
                .build();
    }

    @Builder
    public ItemRequestDto(String itemName, String itemDesc, int price, Seller seller, FileEntity file,SellStatus sellStatus) {
        this.price = price;
        this.itemDesc = itemDesc;
        this.itemName = itemName;
        this.seller = seller;
        this.fileEntity = file;
        this.sellStatus = sellStatus;
    }

}
