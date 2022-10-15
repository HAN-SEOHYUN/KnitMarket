package com.proj.KnitMarket.domain.Member;

import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.dto.AddressDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Seller extends BaseEntity {

    @Id
    @Column(name="seller_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // 회원번호

    @Column(nullable = false)
    private String name; //이름

    @Column
    private String email; //이메일

    @Column
    private String store; //가게이름

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Item> item = new ArrayList<>(); // 판매자가 판매중인 상품들

    @Column
    private String hp; //전화번호

    @Column
    private String accountBank; //계좌은행

    @Column
    private String accountNumber; //계좌번호

    @Column
    private String accountName; //계좌주

    @Column
    private String social_private_key; //소셜 로그인 키


    @Builder
    public Seller(String name, String email,String hp, String social_private_key) {
        this.name = name;
        this.email = email;
        this.hp = hp;
        this.social_private_key = social_private_key;
    }

    public void updateStore(String store, String accountBank, String accountNumber, String accountName){
        this.store = store;
        this.accountBank = accountBank;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
    }

}
