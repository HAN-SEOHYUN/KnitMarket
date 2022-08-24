package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.domain.Order.Cart;
import com.proj.KnitMarket.domain.Order.CartItemRepository;
import com.proj.KnitMarket.domain.Order.CartRepository;
import com.proj.KnitMarket.dto.CartDto;
import com.proj.KnitMarket.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    //cart 만들기 (member 만 넣어서)
    //근데이제 이미  cart 가 있으면 안만듦
    @Transactional Long save(Long userId, Long itemId){
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        CartDto cartDto = CartDto.builder()
                .user(user)
                .build();
        Long cartId = cartRepository.save(cartDto.toEntity()).getId();

        //item 받아서 cartItem 만들기
        Item item = itemRepository.findItemById(itemId);
        CartItemDto cartItemDto = CartItemDto.builder()
                .cart(cartDto.toEntity())
                .item(item)
                .build();

        Long cartItemId = cartItemRepository.save(cartItemDto.toEntity()).getId();

        //cartItem 만든거 cartItemList 에 넣기
        //cartItemList가 이미있는지 없는지 확인해야함


        //만든 리스트 만들어놓은 cart에 add하기


        return userId;
    }




}
