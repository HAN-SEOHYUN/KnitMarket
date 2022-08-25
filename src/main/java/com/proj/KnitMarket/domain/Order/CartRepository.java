package com.proj.KnitMarket.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findCartByUser_Id(Long userId);
}
