package com.proj.KnitMarket.domain.Order;

import com.proj.KnitMarket.Constant.OrderStatus;
import com.proj.KnitMarket.Constant.SellStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findOrderItemByOrder_Id(Long orderId);
    List<OrderItem> findOrderItemByOrder_OrderStatusAndItem_Seller_Id(OrderStatus orderStatus, Long sellerId);

}
