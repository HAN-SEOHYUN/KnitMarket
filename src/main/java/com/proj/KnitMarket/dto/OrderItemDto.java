package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Order.Order;
import com.proj.KnitMarket.domain.Order.OrderItem;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Item item;
    private Order order;

    public OrderItem toEntity(){
        return OrderItem.builder()
                .id(id)
                .order(order)
                .item(item)
                .build();
    }

    @Builder
    public OrderItemDto(Long id, Item item, Order order) {
        this.id = id;
        this.item = item;
        this.order = order;
    }
}
