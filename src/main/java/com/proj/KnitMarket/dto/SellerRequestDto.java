package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Member.Seller;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SellerRequestDto {
    private Long id;
    private String email;
    private String name;
    private String store;

    public Seller toEntity(){
        return Seller.builder()
                .email(email)
                .name(name)
                .build();
    }

    @Builder
    public SellerRequestDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}