package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.Role;
import com.proj.KnitMarket.domain.Member.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String name;

    public User toEntity(){
        return User.builder()
                .email(email)
                .name(name)
                .build();
    }

    @Builder
    public UserRequestDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
