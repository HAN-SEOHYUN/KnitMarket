package com.proj.KnitMarket.domain.Item;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Member.Seller;
import com.proj.KnitMarket.domain.Order.CartItem;
import com.proj.KnitMarket.dto.ItemRequestDto;
import com.proj.KnitMarket.dto.ItemResponseDto;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item")
@Entity
@Where(clause = "is_deleted = false")
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id; //상품코드

    private String itemName; //상품명

    private int price; //상품가격

    private String itemDesc; //상품 상세 설명

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller; //상품 등록자

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "fileEntity_id")
    private FileEntity file;

    @Enumerated(EnumType.STRING)
    private SellStatus sellStatus; //상품 판매 상태

    private boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "id", cascade =CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItemList = new ArrayList<CartItem>();

    @Builder
    public Item(String itemName, int price, String itemDesc, Seller seller, FileEntity file, SellStatus sellStatus, Long id,boolean isDeleted) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.itemDesc = itemDesc;
        this.seller = seller;
        this.file = file;
        this.sellStatus = sellStatus;
        this.isDeleted = isDeleted;
    }

    public void updateItem(ItemRequestDto itemRequestDto) {
        this.itemName = itemRequestDto.getItemName();
        this.price = itemRequestDto.getPrice();
        this.itemDesc = itemRequestDto.getItemDesc();
        this.sellStatus = itemRequestDto.getSellStatus();
        this.file = itemRequestDto.getFileEntity();
    }

    public void updateItemWithoutFile(ItemRequestDto itemRequestDto) {
        this.itemName = itemRequestDto.getItemName();
        this.price = itemRequestDto.getPrice();
        this.itemDesc = itemRequestDto.getItemDesc();
        this.sellStatus = itemRequestDto.getSellStatus();
    }

    public void deleteItem(ItemResponseDto itemResponseDto){
        this.isDeleted = itemResponseDto.isDeleted();
    }
}
