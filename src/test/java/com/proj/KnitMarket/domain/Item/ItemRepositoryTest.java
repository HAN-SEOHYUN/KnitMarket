package com.proj.KnitMarket.domain.Item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    public void cleanup(){
        itemRepository.deleteAll();
    }

    @Test
    public void save(){
        String itemName="상품명";
        String itemDesc ="상품설명";
        int price =4000;

        itemRepository.save(Item.builder().itemName(itemName).price(price).itemDesc(itemDesc).build());




    }

}