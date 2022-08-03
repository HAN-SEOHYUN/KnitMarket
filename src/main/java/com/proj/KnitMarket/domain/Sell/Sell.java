package com.proj.KnitMarket.domain.Sell;


import com.proj.KnitMarket.domain.SellItems.SellItems;
import com.proj.KnitMarket.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seller_id")
    private User seller; // 판매자 번호

    @OneToMany(mappedBy = "sell")
    private List<SellItems> sellItems = new ArrayList<>();

    private int count; // 판매한건당 총 판매상품 개수




}
