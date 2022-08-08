package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private String hp;
    private String email;
    private String name;

    public Member toEntity(){
        return Member.builder()
                .hp(hp)
                .email(email)
                .name(name)
                .build();
    }

    @Builder
    public MemberRequestDto(String hp, String email, String name) {
        this.hp = hp;
        this.email = email;
        this.name = name;
    }
}
