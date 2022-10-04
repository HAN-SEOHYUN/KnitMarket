package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Member.Seller;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SellerResponseDto {
    private Long id;
    private String email;
    private String name;
    private List<ItemResponseDto> items;
    private String store;
    private String accountBank;
    private String accountNum;
    private String accountName;

    //entity to DTO
    public SellerResponseDto(Seller entity){
        this.id=entity.getId();
        this.email=entity.getEmail();
        this.name=entity.getName();
        this.items = entity.getItem().stream().map(ItemResponseDto::new).collect(Collectors.toList());
        this.store = entity.getStore();
        this.accountBank = entity.getAccountBank();
        this.accountNum = entity.getAccountNumber();
        this.accountName = entity.getAccountName();
    }

}
