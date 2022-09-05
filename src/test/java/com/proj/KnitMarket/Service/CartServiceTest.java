package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Order.CartItem;
import com.proj.KnitMarket.domain.Order.CartItemRepository;
import com.proj.KnitMarket.domain.Order.CartRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    @DisplayName("장바구니 전체 상품삭제")
    void test1(){
        //given
        Long cartId = 15L;

        //when
        cartItemRepository.deleteCartItemByCart_Id(cartId);

        //then
        assertEquals(0,cartItemRepository.count());


    }

}