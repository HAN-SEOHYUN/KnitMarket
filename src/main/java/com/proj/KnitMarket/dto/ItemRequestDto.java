package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Member.Member;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
public class ItemRequestDto {
    private String itemName;
    private int price;
    private String itemDesc;
    private Member member;

    //private SellStatus sellStatus;


    public Item toEntity() {
        return Item.builder()
                .member(member)
                .itemName(itemName)
                .price(price)
                .itemDesc(itemDesc)
                .build();
    }

    public void setMember(Member member){
        this.member=member;
    }

    @Builder
    public ItemRequestDto(Member member, String itemName, String itemDesc, int price) {
        this.member = member;
        this.price = price;
        this.itemDesc = itemDesc;
        this.itemName = itemName;
    }

}
