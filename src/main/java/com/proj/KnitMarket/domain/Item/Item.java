package com.proj.KnitMarket.domain.Item;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.User.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Table(name="item")
@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //상품코드

    private String itemName; //상품명

    private int price; //상품가격

    private String itemDesc; //상품 상세 설명

   // private SellStatus sellStatus; //상품 판매 상태 (0 : 판매중 / 1 : 품절)

    @Builder
    public Item(Long id,String itemName, int price, String itemDesc) {
        this.id=id;
        this.itemName = itemName;
        this.price = price;
        this.itemDesc = itemDesc;
    }
}
