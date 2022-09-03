package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.OrderStatus;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Order.Order;
import com.proj.KnitMarket.domain.Order.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private User user;
    private List<OrderItem> orderItems;

    public Order toEntity(){
        return Order.builder()
                .user(user)
                .orderItems(orderItems)
                .orderStatus(OrderStatus.ORDER)
                .build();
    }

    @Builder
    public OrderDto(Long id, User user, List<OrderItem> orderItems){
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
    }
}
