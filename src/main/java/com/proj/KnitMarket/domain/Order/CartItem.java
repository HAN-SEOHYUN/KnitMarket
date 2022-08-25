package com.proj.KnitMarket.domain.Order;

import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Item.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
CartItem은 Cart가 먼저 생성이되어야 생성될 수 있고,
Cart가 삭제되면 마찬가지로 삭제된다.
이로서 CartItem의 생명주기는 Cart에 의존적이다
*/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public CartItem(Long id, Cart cart, Item item, LocalDateTime regTime){
        this.id=id;
        this.cart = cart;
        this.item = item;
        this.setRegTime(regTime);
    }
}
