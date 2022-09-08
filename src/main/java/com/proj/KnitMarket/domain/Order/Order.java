package com.proj.KnitMarket.domain.Order;

import com.proj.KnitMarket.Constant.OrderStatus;
import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Member.User;
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
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="order_id")
    private Long id; //주문번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.CANCEL; //주문상태

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    private int totalPrice;

    @Builder
    public Order(Long id, User user, OrderStatus orderStatus, List<OrderItem>orderItems, int totalPrice, LocalDateTime regTime) {
        this.id = id;
        this.user = user;
        this.orderStatus = orderStatus;
        this.orderItems=orderItems;
        this.totalPrice = totalPrice;
    }

}
