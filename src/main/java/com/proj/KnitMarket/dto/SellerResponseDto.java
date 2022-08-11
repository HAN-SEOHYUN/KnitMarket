package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Member.Seller;
import lombok.Getter;

@Getter
public class SellerResponseDto {
    private Long id;
    private String email;
    private String name;

    //entity to DTO
    public SellerResponseDto(Seller entity){
        this.id=entity.getId();
        this.email=entity.getEmail();
        this.name=entity.getName();
    }

}
