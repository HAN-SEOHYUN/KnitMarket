package com.proj.KnitMarket.domain.User;

import com.proj.KnitMarket.domain.Cart.Cart;
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
    private String accountBank; //은행
    private String accountNo; //계좌번호

    @OneToOne(mappedBy = "user")
    private Cart cart; //장바구니









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
