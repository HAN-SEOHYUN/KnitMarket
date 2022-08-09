package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Member.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {
    private String email;
    private String name;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .name(name)
                .build();
    }

    @Builder
    public MemberRequestDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
