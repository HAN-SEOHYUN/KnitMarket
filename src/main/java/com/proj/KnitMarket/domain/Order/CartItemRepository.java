package com.proj.KnitMarket.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByCart_Id(Long cartId);
}
