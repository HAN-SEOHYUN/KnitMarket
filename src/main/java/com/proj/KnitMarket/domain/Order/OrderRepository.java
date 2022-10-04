package com.proj.KnitMarket.domain.Order;

import com.proj.KnitMarket.Constant.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findOrderById(Long orderId);
    List<Order> findOrdersByUser_Id(Long userId);
    List<Order> findOrdersByUser_IdAndOrderStatus(Long userId, OrderStatus orderStatus);

}
