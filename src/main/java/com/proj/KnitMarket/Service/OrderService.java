package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.domain.Order.Order;
import com.proj.KnitMarket.domain.Order.OrderItem;
import com.proj.KnitMarket.domain.Order.OrderRepository;
import com.proj.KnitMarket.dto.OrderDto;
import com.proj.KnitMarket.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

     public Long order(Long itemId,String email){
         //orderItem 객체생성
         List<OrderItem> orderItems = new ArrayList<>();
         Item item = itemRepository.findItemById(itemId);
         log.info("itmeId = {}",itemId);
         OrderItemDto orderItemDto = OrderItemDto.builder()
                 .item(item)
                 .build();

         orderItems.add(orderItemDto.toEntity());

         //order 객체생성
         User user = userRepository.findByEmail(email);
         log.info("userName = {}",user.getName());
         OrderDto orderDto = OrderDto.builder()
                 .user(user)
                 .orderItems(orderItems)
                 .build();

         //order 객체 db저장 (Cascade로 인해 OrderItem 객체도 같이 저장)
         Order order = orderRepository.save(orderDto.toEntity());

         //item SellStatus 변경
         item.updateSellStatus(SellStatus.SOLD_OUT);
         itemRepository.save(item);

         log.info("orderId = {}",order.getId());

        return order.getId();
     }


}
