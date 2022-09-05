package com.proj.KnitMarket.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findOrderById(Long orderId);

}
