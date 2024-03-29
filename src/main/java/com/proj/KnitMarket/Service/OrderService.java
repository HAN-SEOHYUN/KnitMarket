package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.Constant.OrderStatus;
import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.domain.Order.*;
import com.proj.KnitMarket.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;

    //단일상품주문
    @Transactional
     public OrderDto order(Long itemId, String email){
        //user 정보를 가진 객체 생성
        User user = userRepository.findByEmail(email);
        Order order = createOrder(user);

        List<OrderItem> orderItems = new ArrayList<>();
        Item item = itemRepository.findItemById(itemId);

        OrderItem orderItem = addOrderItem(item,order);
        orderItems.add(orderItem);

        order = addOrderInfo(orderItems,order,item.getPrice());

        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .user(order.getUser())
                .orderStatus(order.getOrderStatus())
                .orderItems(order.getOrderItems())
                .totalPrice(order.getTotalPrice())
                .build();

        return orderDto;
     }

     //List<orderItem> => List<orderItemDto>
    @Transactional
    public List<OrderItemDto> entityToDto(OrderDto orderDto){
        List<OrderItem> orderItemList = orderDto.getOrderItems();
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();

        for(OrderItem orderItem : orderItemList){
            OrderItemDto orderItemDto = OrderItemDto.builder()
                    .id(orderItem.getId())
                    .item(orderItem.getItem())
                    .order(orderItem.getOrder())
                    .build();
            orderItemDtoList.add(orderItemDto);
        }
        return orderItemDtoList;
    }

     //장바구니 상품을 주문상품에 넣어주는 메서드
    @Transactional
    public OrderItem addOrderItem(Item item,Order order){
        OrderItemDto orderItemDto = OrderItemDto.builder()
                .item(item)
                .order(order)
                .build();

        OrderItem orderItem =  orderItemRepository.save(orderItemDto.toEntity());
        return orderItem;
    }

    //주문정보 입력해주는 메서드
    @Transactional
    public Order addOrderInfo(List<OrderItem> orderItems,Order order,int totalPrice){
        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .user(order.getUser())
                .orderItems(orderItems)
                .orderStatus(OrderStatus.CANCEL)
                .totalPrice(totalPrice)
                .build();

       return orderRepository.save(orderDto.toEntity());
    }

    //주문객체를 생성해주는 메서드
    public Order createOrder(User user){
        OrderDto orderDto = OrderDto.builder()
                .user(user)
                .build();
       return orderRepository.save(orderDto.toEntity());
    }

     //장바구니 상품 전체 주문
    @Transactional
    public OrderDto orders(Long userId){
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        //User정보를 가진 Order 객체 생성
        Order order = createOrder(user);
        log.info("생성된 order={}",order.getId());

        int result = 0;

        //user의 장바구니와 장바구니 속 상품 목록 가져오기
        Cart cart = cartService.findUserCart(user.getId());
        List<CartItemDto> cartItems = cartService.getCartItemList(user.getId());

        int totalPrice = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItemDto cartItemDto : cartItems){
            totalPrice += cartItemDto.getItem().getPrice();

            //Item 을 주문상품(OrderItem) 에 넣어줌
            OrderItem orderItem = addOrderItem(cartItemDto.getItem(), order);
            orderItems.add(orderItem); //주문상품들을 주문상품 목록에 추가
        }

        //생성된 order 객체에 orderItems 정보 넣기
        order = addOrderInfo(orderItems,order,totalPrice);
        log.info("수정된 order ={}",order.getId());

        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .user(order.getUser())
                .orderItems(order.getOrderItems())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .build();

        return orderDto;
    }

    //결제 후 staus 변경 (orderStatus=결제완료 , itemSellStatus=품절)
    @Transactional
    public OrderDto changeStatus(Long orderId){
      Order order=orderRepository.findOrderById(orderId);
      List<OrderItem> orderItemList = order.getOrderItems();

        //item SellStatus 변경
        for(OrderItem orderItem : orderItemList){
           Item item= orderItem.getItem();
           item.updateSellStatus(SellStatus.SOLD_OUT);
           itemRepository.save(item);
        }

        //order OrderStatus 변경
        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .user(order.getUser())
                .orderStatus(OrderStatus.ORDER)
                .orderItems(order.getOrderItems())
                .totalPrice(order.getTotalPrice())
                .regTime(order.getRegTime())
                .build();
        orderRepository.save(orderDto.toEntity());

        return orderDto;
    }

}
