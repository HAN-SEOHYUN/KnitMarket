package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Order.Order;
import com.proj.KnitMarket.domain.Order.OrderItem;
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
                .build();

    }

}
