package com.proj.KnitMarket.domain.Item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long>{
    Item findItemById(Long id);
    List<Item> findBySeller_Id(Long sellerId);

}
