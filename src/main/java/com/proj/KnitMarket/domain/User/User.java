package com.proj.KnitMarket.domain.User;

import com.proj.KnitMarket.Constant.Role;
import com.proj.KnitMarket.domain.Address.Address;
import com.proj.KnitMarket.domain.Cart.Cart;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.OrderInfo.OrderInfo;
import com.proj.KnitMarket.domain.OrderItems.OrderItems;
import com.proj.KnitMarket.domain.Sell.Sell;
import com.proj.KnitMarket.domain.SellItems.SellItems;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // 회원번호

    @Column(nullable = false)
    private String name; //이름

    @Column
    private String hp; //전화번호
    private String email; //이메일

    @Embedded
    @Column(nullable = true)
    private Address address; //주소번호

    private String accountBank; //은행
    private String accountNo; //계좌번호

    @Enumerated(EnumType.STRING)
    private Role role; // 회원유형 (일반회원 / 판매자)

    @OneToMany(mappedBy = "user")
    private List<OrderInfo> order = new ArrayList<>(); // 구매자의 주문정보

    @OneToMany(mappedBy = "user")
    private List<OrderItems> orderItem = new ArrayList<>(); // 구매자의 주문상세정보
/*
    @OneToMany(mappedBy = "seller")
    private List<Sell> sell = new ArrayList<>(); // 판매자의 판매정보

    @OneToMany(mappedBy = "seller")
    private List<SellItems> sellerItem = new ArrayList<>(); // 판매자의 판매상세정보

    @OneToMany(mappedBy = "seller")
    private List <Item> item = new ArrayList<>(); // 판매자가 판매중인 상품들*/

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate regDate; // 가입일

    @Builder
    public User(long id, String name, String hp, String email, Address address, String accountBank, String accountNo, Role role, List<OrderInfo> order, List<OrderItems> orderItem, LocalDate regDate) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.email = email;
        this.address = address;
        this.accountBank = accountBank;
        this.accountNo = accountNo;
        this.role = role;
        this.order = order;
        this.orderItem = orderItem;
        this.regDate = regDate;
    }
}

