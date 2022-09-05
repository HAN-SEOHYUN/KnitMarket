package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.Constant.OrderStatus;
import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.domain.Order.*;
import com.proj.KnitMarket.dto.CartItemDto;
import com.proj.KnitMarket.dto.OrderDto;
import com.proj.KnitMarket.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
                 .orderStatus(OrderStatus.CANCEL)
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

     //장바구니 상품을 주문상품에 넣어주는 메서드
    @Transactional
    public OrderItem addOrderItem(Item item,Order order){
        OrderItemDto orderItemDto = OrderItemDto.builder()
                .item(item)
                .order(order)
                .build();

        OrderItem orderItem =  orderItemRepository.save(orderItemDto.toEntity());
        log.info("orderItem={}",orderItem.getId());
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
    public int orders(Long userId){
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

            // 이미 판매된 상품이 장바구니에 포함되어있으면 result = 1
            if(cartItemDto.getItem().getSellStatus()!=SellStatus.SELL){
                result= 1;
                return result;
            }
            totalPrice += cartItemDto.getItem().getPrice();

            //Item 을 주문상품(OrderItem) 에 넣어줌
            OrderItem orderItem = addOrderItem(cartItemDto.getItem(), order);
            orderItems.add(orderItem); //주문상품들을 주문상품 목록에 추가
        }

        //생성된 order 객체에 orderItems 정보 넣기
        order = addOrderInfo(orderItems,order,totalPrice);
        log.info("수정된 order ={}",order.getId());

        //장바구니 비우기 => 결제할때 사용
        // cartService.cartRemoveAll(cart.getId());
        return result;
    }

}
