package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Member.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String name;
    private String hp;

    public User toEntity(){
        return User.builder()
                .email(email)
                .name(name)
                .hp(hp)
                .build();
    }

    @Builder
    public UserRequestDto(String email, String name,String hp) {
        this.email = email;
        this.name = name;
        this.hp = hp;
    }
}
