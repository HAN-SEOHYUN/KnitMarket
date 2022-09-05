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
    private OrderStatus orderStatus;

    public Order toEntity(){
        return Order.builder()
                .user(user)
                .orderItems(orderItems)
                .orderStatus(orderStatus)
                .build();
    }

    @Builder
    public OrderDto(Long id, OrderStatus orderStatus,User user, List<OrderItem> orderItems){
        this.id = id;
        this.user = user;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }
}
