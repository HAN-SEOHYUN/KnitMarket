/*
package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Member.Address;
import com.proj.KnitMarket.domain.Member.AddressRepository;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.dto.AddressDto;
import com.proj.KnitMarket.dto.OrderDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;


    @BeforeEach
    void clean() {
        addressRepository.deleteAll();
    }

    @Test
    @DisplayName("주소등록")
    void test1() throws IOException {
        //given
        AddressDto addressDto = AddressDto.builder()
                .address("주소")
                .addressDetail("상세주소")
                .zipcode("12345")
                .enterMethod("공동현관 출입방법")
                .build();
        //when
        Long addresssId = userService.save_address(addressDto, 3L);

        //then
        Assertions.assertEquals(1L, addressRepository.count());
        Address address = addressRepository.findAll().get(0);
        assertEquals("주소", address.getAddress());
        assertEquals("상세주소", address.getAddressDetail());
    }

    @Test
    @DisplayName("주소 조회")
    void test2() {
        //given
        User user = userRepository.findById(3L).orElseThrow(EntityNotFoundException::new);
        Address address = Address.builder()
                .address("주소")
                .user(user)
                .build();
        addressRepository.save(address);

        //when
        AddressDto addressDto = userService.getAddress(3L);

        //then
        assertNotNull(addressDto);
        assertEquals(1L, addressRepository.count());
        assertEquals("주소", addressDto.getAddress());
    }

    @Test
    @DisplayName("주소수정")
    void test3() {
        //given
        User user = userRepository.findById(3L).orElseThrow(EntityNotFoundException::new);
        Address address = Address.builder()
                .addressDetail("주소 수정 전")
                .address("주소수정테스트")
                .enterMethod("없음")
                .zipcode("12345")
                .user(user)
                .build();
        Long addressId = addressRepository.save(address).getId();

        Address savedAddress = addressRepository.findById(addressId).orElseThrow(() -> new RuntimeException("저장된 주소가 없습니다. addressId=" + address.getId()));

        AddressDto addressDto = AddressDto.builder()
                .id(savedAddress.getId())
                .addressDetail("주소수정됨")
                .address("주소수정테스트")
                .enterMethod("없음")
                .zipcode("12345")
                .user(user)
                .build();

        savedAddress.updateAddress(addressDto);

        //when
        addressRepository.save(savedAddress);

        //then
        Address changedAddress = addressRepository
                .findById(addressId).orElseThrow(() -> new RuntimeException("주소가 존재하지 않습니다. addressId=" + address.getId()));
        assertEquals("주소수정됨", changedAddress.getAddressDetail());

    }

    @Test
    @DisplayName("orderItem 갯수세기")
    void test4() {
        List<OrderDto> orderDtoList = userService.selectOrderList(3L);

        for (OrderDto orderDto : orderDtoList) {
            assertEquals("height 테스트용4 외 2개", orderDto.getOrderName());
        }
    }

}*/
