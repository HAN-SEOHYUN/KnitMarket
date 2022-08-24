package com.proj.KnitMarket.domain.Order;
import com.proj.KnitMarket.domain.Member.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query ("SELECT c FROM Cart c WHERE c.user.name = ?1")
    Cart findByUser(String name) throws NullPointerException;
    Cart findByUser_id(Long userId);

    Cart findCartByUser_Id(Long userId);

    boolean existsByUser_Id(Long userId);



}
