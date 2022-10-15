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
    private String social_private_key;

    public User toEntity(){
        return User.builder()
                .email(email)
                .name(name)
                .hp(hp)
                .social_private_key(social_private_key)
                .build();
    }

    @Builder
    public UserRequestDto(String email, String name,String hp, String social_private_key) {
        this.email = email;
        this.name = name;
        this.hp = hp;
        this.social_private_key=social_private_key;
    }
}
