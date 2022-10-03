package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.Constant.OrderStatus;
import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.*;
import com.proj.KnitMarket.domain.Order.Order;
import com.proj.KnitMarket.domain.Order.OrderItem;
import com.proj.KnitMarket.domain.Order.OrderItemRepository;
import com.proj.KnitMarket.domain.Order.OrderRepository;
import com.proj.KnitMarket.dto.ItemResponseDto;
import com.proj.KnitMarket.dto.OrderDto;
import com.proj.KnitMarket.dto.SellerRequestDto;
import com.proj.KnitMarket.dto.SellerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final ItemRepository itemRepository;
    private final HttpSession httpSession;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;

    @Transactional //회원등록
    public Long save(SellerRequestDto sellerDto) {
        return sellerRepository.save(sellerDto.toEntity()).getId();
    }

    //email 로 SELLER 찾기
    @Transactional
    public SellerResponseDto findByEmail(String email) {
        Seller entity = sellerRepository.findByEmail(email);
        return new SellerResponseDto(entity);
    }

    //ID로 SELLER 찾기
    @Transactional
    public SellerResponseDto findById(Long sellerId){
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(EntityNotFoundException::new);
        return new SellerResponseDto(seller);
    }

    //SELLER 존재여부 CHECK
    @Transactional
    public boolean existsByEmail(String email) {
        return sellerRepository.existsByEmail(email);
    }

    //판매자가 게시한 상품 조회 (삭제된 상품 제외)
    @Transactional
    public List<ItemResponseDto> getMyItemList(Long sellerId) {
        List<Item> itemLists = itemRepository.findBySeller_Id(sellerId);
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        List<OrderItem> orderItemList = orderItemRepository.findOrderItemByOrder_OrderStatusAndItem_Seller_Id(OrderStatus.ORDER, sellerId);


        for (Item item : itemLists) {
            ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                    .id(item.getId())
                    .itemName(item.getItemName())
                    .price(item.getPrice())
                    .regTime(item.getRegTime())
                    .sellStatus(item.getSellStatus())
                    .orginFileName(item.getFile().getOrginFileName())
                    .build();

            for(OrderItem orderItem : orderItemList){
                if(Objects.equals(orderItem.getItem().getId(), item.getId())){
                    itemResponseDto.setOrderId(orderItem.getOrder().getId());
                }
            }
            itemResponseDtoList.add(itemResponseDto);
        }
        return itemResponseDtoList;
    }

    //판매자 정보 등록 및 수정
    @Transactional
    public void updateStore(Long sellerId, String store , String accountBank, String accountNum, String accountName){
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(EntityNotFoundException::new);
        seller.updateStore(store,accountBank, accountNum, accountName);
        sellerRepository.save(seller);
    }

    //OrderItem에서 OrderId 찾기
    @Transactional
    public void findOrderId(){



    }



    //orderId 로 배송정보 조회
    @Transactional
    public OrderDto getOrderInfo(Long orderId){
        List<OrderItem> orderItemList = orderItemRepository.findOrderItemByOrder_Id(orderId);
        Order order = orderRepository.findOrderById(orderId);
        Address address = addressRepository.findByUser_Id(order.getUser().getId());

        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .user(order.getUser())
                .orderItems(orderItemList) // order.getOrderItemList
                .totalPrice(order.getTotalPrice())
                .regTime(order.getRegTime())
                .address(address)
                .build();

        return orderDto;
    }

}
