package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Order.OrderItemRepository;
import com.proj.KnitMarket.domain.Order.OrderRepository;
import com.proj.KnitMarket.dto.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void clean(){
        orderItemRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("단일주문정보입력")
    void test1() {
        //given
        Long itemId =28L;
        String email = "000312@daum.net";

        //when
        orderService.order(itemId,email);

        //then
        assertEquals(1L,orderRepository.count());
    }

    @Test
    @DisplayName("주문정보 입력테스트")
    void test2(){
        //given
        Long userId = 3L;

        //when
        OrderDto orderDto = orderService.orders(userId);

        //then

    }




}