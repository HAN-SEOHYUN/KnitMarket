package com.proj.KnitMarket.domain.Seller;

import com.proj.KnitMarket.domain.Item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @OneToMany(mappedBy = "seller")
    List <Item> itemList = new ArrayList<Item>(); //판매자가 등록한 상품목록





}
