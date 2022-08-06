package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.Item;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String itemName;
    private int price;
    private String itemDesc;

    //private SellStatus sellStatus;

    public Item toEntity(){
        Item item = Item.builder()
                .id(id)
                .itemName(itemName)
                .price(price)
                .itemDesc(itemDesc)
                .build();
        return item;
    }

    @Builder
    public ItemDto(Long id, String itemName, int price, String itemDesc) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.itemDesc = itemDesc;
    }
}
