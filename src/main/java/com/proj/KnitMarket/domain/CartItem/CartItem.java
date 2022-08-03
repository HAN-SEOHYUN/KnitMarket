package com.proj.KnitMarket.domain.CartItem;


import com.proj.KnitMarket.domain.Cart.Cart;
import com.proj.KnitMarket.domain.Item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //장바구니에 담긴 아이템 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="cart_id")
    private Cart cart; // 장바구니 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item; // 아이템번호





}
