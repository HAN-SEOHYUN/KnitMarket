package com.proj.KnitMarket.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findByCart_Id(Long cartId);
    boolean existsCartItemByCart_IdAndItem_Id(Long cartId, Long itemId);

    @Override
    void deleteById(Long cartItemId);

    void deleteCartItemByCart_Id(Long cartId);
}
