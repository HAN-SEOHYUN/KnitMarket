package com.proj.KnitMarket.domain.Item;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Member.Seller;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.*;

import javax.persistence.*;

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

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="fileEntity_id")
    private FileEntity file;

    @Enumerated(EnumType.STRING)
   private SellStatus sellStatus; //상품 판매 상태

    @Builder
    public Item(String itemName, int price, String itemDesc,Seller seller,FileEntity file,SellStatus sellStatus, Long id) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.itemDesc = itemDesc;
        this.seller = seller;
        this.file = file;
        this.sellStatus = sellStatus;
    }

    public void updateItem(ItemRequestDto itemRequestDto){
        this.itemName = itemRequestDto.getItemName();
        this.price = itemRequestDto.getPrice();
        this.itemDesc = itemRequestDto.getItemDesc();
        this.sellStatus = itemRequestDto.getSellStatus();
    }
}
