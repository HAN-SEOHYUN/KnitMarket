package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.OrderStatus;
import com.proj.KnitMarket.domain.Member.Address;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Order.Order;
import com.proj.KnitMarket.domain.Order.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private User user;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private int totalPrice;
    private LocalDateTime regTime;
    private String orderName;
    private Address address;

    public Order toEntity(){
        return Order.builder()
                .id(id)
                .user(user)
                .orderItems(orderItems)
                .orderStatus(orderStatus)
                .totalPrice(totalPrice)
                .build();
    }

    @Builder
    public OrderDto(Long id, OrderStatus orderStatus,User user, List<OrderItem> orderItems,int totalPrice,LocalDateTime regTime,String orderName, Address address){
        this.id = id;
        this.user = user;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.regTime = regTime;
        this.orderName = orderName;
        this.address = address;
    }

}
