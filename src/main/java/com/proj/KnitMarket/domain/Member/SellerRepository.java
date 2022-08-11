package com.proj.KnitMarket.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Seller findByEmail(String email);
    boolean existsByEmail(String email);
}
