package com.proj.KnitMarket.domain.OrderItems;


import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.OrderInfo.OrderInfo;
import com.proj.KnitMarket.domain.SellItems.SellItems;
import com.proj.KnitMarket.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderItems {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="orderInfo_id")
    private OrderInfo order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="item_id")
    private Item item;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sellItems_id")
    private SellItems sellItems; // 주문상품에 매핑되는 판매상품

    private int isCancel; // (0:주문완료 / 1:주문취소)





}
