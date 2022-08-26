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
   // private String hp; //전화번호

    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

