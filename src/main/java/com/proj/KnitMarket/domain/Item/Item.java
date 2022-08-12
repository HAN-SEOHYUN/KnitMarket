package com.proj.KnitMarket.domain.Item;

import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Member.Seller;
import lombok.*;

import javax.persistence.*;
import java.io.File;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="item")
@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //상품코드

    private String itemName; //상품명

    private int price; //상품가격

    private String itemDesc; //상품 상세 설명

    @ManyToOne
    @JoinColumn(name="seller_id")
    private Seller seller; //상품 등록자

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private FileEntity file;

   // private SellStatus sellStatus; //상품 판매 상태 (0 : 판매중 / 1 : 품절)

    @Builder
    public Item(String itemName, int price, String itemDesc,Seller seller) {
        this.itemName = itemName;
        this.price = price;
        this.itemDesc = itemDesc;
        this.seller = seller;
    }

    public void update(String itemName, int price, String itemDesc){
        this.itemName = itemName;
        this.price = price;
        this.itemDesc = itemDesc;
    }
}
