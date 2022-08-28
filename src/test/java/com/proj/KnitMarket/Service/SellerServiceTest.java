package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Member.Seller;
import com.proj.KnitMarket.domain.Member.SellerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SellerServiceTest {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private SellerRepository sellerRepository;

    @Test
    @DisplayName("가게명 등록")
    void test1() {
        //given
        Seller seller = Seller.builder()
                .name("테스트")
                .email("test@daum.net")
                .build();
        Long sellerId = sellerRepository.save(seller).getId();

        Seller savedSeller = sellerRepository.findById(sellerId).orElseThrow(() -> new RuntimeException("판매자 정보가 없습니다. sellerId=" + sellerId));
        savedSeller.updateStore("가게명");

        //when
        sellerRepository.save(savedSeller);

        //then
        Seller changedSeller = sellerRepository.findById(sellerId).orElseThrow(() -> new RuntimeException("판매자 정보가 없습니다. sellerId=" + sellerId));
        assertEquals("가게명", changedSeller.getStore());

    }
}