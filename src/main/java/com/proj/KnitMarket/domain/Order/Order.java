package com.proj.KnitMarket.domain.Order;

import com.proj.KnitMarket.Constant.OrderStatus;
import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.dto.OrderDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="order_id")
    private Long id; //주문번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime orderDate; //주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문상태

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order(User user, LocalDateTime orderDate, OrderStatus orderStatus,List<OrderItem>orderItems) {
        this.user = user;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderItems=orderItems;
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getItem().getPrice();
        }
        return totalPrice;
    }





}
