package com.proj.KnitMarket.domain.Order;

import com.proj.KnitMarket.Constant.OrderStatus;
import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Item.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_item")
@Entity
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;


    @Builder
    public OrderItem(Long id,Item item, Order order) {
        this.id = id;
        this.item = item;
        this.order = order;
    }

}
