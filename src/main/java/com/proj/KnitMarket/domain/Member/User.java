package com.proj.KnitMarket.domain.Member;

import com.proj.KnitMarket.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // 회원번호

    @Column(nullable = false)
    private String name; //이름

    @Column
    private String email; //이메일

    @Column
    private String hp; //전화번호

    @Column
    private String social_private_key;

    @Builder
    public User(String name, String email,String hp,String social_private_key) {
        this.name = name;
        this.email = email;
        this.hp = hp;
        this.social_private_key=social_private_key;
    }
}

