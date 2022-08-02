package com.proj.KnitMarket.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String hp;
    private String email;
    private String addressId;
    private String accountBank;
    private String accountNo;


    @Builder
    public User(long id, String name, String hp, String email, String addressId, String accountBank, String accountNo) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.email = email;
        this.addressId = addressId;
        this.accountBank = accountBank;
        this.accountNo = accountNo;
    }
}
