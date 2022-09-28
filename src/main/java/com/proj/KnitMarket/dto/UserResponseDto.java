package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Member.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String hp;

    //entity to DTO
    public UserResponseDto(User entity){
        this.id=entity.getId();
        this.email=entity.getEmail();
        this.name=entity.getName();
        this.hp = entity.getHp();
    }

}
