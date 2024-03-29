package com.proj.KnitMarket.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    User findByIdAndEmail(Long id, String email);

}
