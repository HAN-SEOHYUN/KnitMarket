package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class ItemDto {
    private Long id;

    @NotNull(message = "상품명은 필수입력값입니다")
    private String itemName;
    @NotNull(message = "가격은 필수입력값입니다")
    private int price;
    @NotNull(message = "상품설명은 필수입력값입니다")
    private String itemDesc;

    @Nullable
    private SellStatus sellStatus;

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this,Item.class);
    }

    public static ItemDto of(Item item){
        return modelMapper.map(item,ItemDto.class);
    }



}
