package com.proj.KnitMarket.domain.Member;

import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Item.Item;
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
    // private String hp; //전화번호

/*
    @Embedded
    @Column(nullable = true)
    private Address address; //주소번호

    private String accountBank; //은행
    private String accountNo; //계좌번호



/*    @OneToMany(mappedBy = "user")
    private List<OrderInfo> order = new ArrayList<>(); // 구매자의 주문정보

    @OneToMany(mappedBy = "user")
    private List<OrderItems> orderItem = new ArrayList<>(); // 구매자의 주문상세정보*/

/*    @OneToMany(mappedBy = "seller")
    private List<Sell> sell = new ArrayList<>(); // 판매자의 판매정보

    @OneToMany(mappedBy = "seller")
    private List<SellItems> sellerItem = new ArrayList<>(); // 판매자의 판매상세정보*/

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Item> item = new ArrayList<>(); // 판매자가 판매중인 상품들

    @Builder
    public Seller(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
