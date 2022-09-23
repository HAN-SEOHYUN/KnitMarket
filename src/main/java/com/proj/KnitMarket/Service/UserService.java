package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.Constant.OrderStatus;
import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Member.Address;
import com.proj.KnitMarket.domain.Member.AddressRepository;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.domain.Order.Order;
import com.proj.KnitMarket.domain.Order.OrderItem;
import com.proj.KnitMarket.domain.Order.OrderRepository;
import com.proj.KnitMarket.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final HttpSession httpSession;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public User save(UserRequestDto userDto) {
        return userRepository.save(userDto.toEntity());
    }

    @Transactional
    public UserResponseDto findByEmail(String email) {
        User entity = userRepository.findByEmail(email);
        return new UserResponseDto(entity);
    }

    @Transactional
    public UserResponseDto findById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    //주소등록
    @Transactional
    public Long save_address(AddressDto addressDto,Long userId)throws IOException{
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        addressDto.setUser(user);

        if(Objects.equals(addressDto.getEnterMethod(), "") ||addressDto.getEnterMethod().isEmpty()){
            addressDto.setEnterMethod("없음");
        }
        return addressRepository.save(addressDto.toEntity()).getId();
    }

    //주소조회
    @Transactional
    public AddressDto getAddress(Long userId){
        Address address = addressRepository.findByUser_Id(userId);
        AddressDto addressDto = new AddressDto();
        if(address!=null){
            addressDto = AddressDto.builder()
                    .id(address.getId())
                    .user(address.getUser())
                    .address(address.getAddress())
                    .addressDetail(address.getAddressDetail())
                    .zipcode(address.getZipcode())
                    .enterMethod(address.getEnterMethod())
                    .build();
        }

        return addressDto;
    }

    //주소수정
    @Transactional
    public Long updateAddress(Long addressId, AddressDto addressDto){
        Address address = addressRepository.findById(addressId).orElseThrow(EntityNotFoundException::new);
        Long userId = (Long)httpSession.getAttribute("id");
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        addressDto.setUser(user);
        if(Objects.equals(addressDto.getEnterMethod(), "") ||addressDto.getEnterMethod().isEmpty()){
            addressDto.setEnterMethod("없음");
        }
        address.updateAddress(addressDto);
        addressRepository.save(address);
        return address.getId();
    }

    //사용자의 주문목록 조회
    //각 주문의 상품개수를 조회하여 orderName을 만들어 orderDto 로 리턴해주는 메서드
    @Transactional
    public List<OrderDto> selectOrderList(Long userId){
        List<Order> orderList = orderRepository.findOrdersByUser_IdAndOrderStatus(userId, OrderStatus.ORDER);
        List <OrderDto> orderDtoList = new ArrayList<>();

        int itemQty = 0;
        String orderName = "";
        OrderDto orderDto = new OrderDto();

        for(Order order : orderList){ //사용자의 주문갯수만큼
            List<OrderItem> orderItemList = order.getOrderItems();

            for(OrderItem orderItem : orderItemList){ //주문1개의 상품개수만큼
                itemQty ++;
                orderName = orderItem.getItem().getItemName();
            }

            if(itemQty>1){
                itemQty -= 1;
                orderName = orderName + " 외 " + itemQty +"개";
            }

            orderDto = OrderDto.builder()
                    .id(order.getId())
                    .orderName(orderName)
                    .totalPrice(order.getTotalPrice())
                    .regTime(order.getRegTime())
                    .build();

            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

}
