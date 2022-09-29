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
    private String hp;

    public Seller toEntity(){
        return Seller.builder()
                .email(email)
                .name(name)
                .hp(hp)
                .build();
    }

    @Builder
    public SellerRequestDto(String email, String name,String hp) {
        this.email = email;
        this.name = name;
        this.hp = hp;
    }
}