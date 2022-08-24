package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.domain.Order.Cart;
import com.proj.KnitMarket.domain.Order.CartItem;
import com.proj.KnitMarket.domain.Order.CartItemRepository;
import com.proj.KnitMarket.domain.Order.CartRepository;
import com.proj.KnitMarket.dto.CartDto;
import com.proj.KnitMarket.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

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
    @Transactional
    public Cart save(Long userId, Long itemId) {
        log.info("cartService");
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        log.info("user ={}",user.getName());

        CartDto cartDto = CartDto.builder()
                .user(user)
                .build();
        Cart cart = cartRepository.save(cartDto.toEntity());
        log.info("cartUser = {}", cart.getUser().getId());

        /*//cartItemList가 이미있는지 없는지 확인해야함
        List<CartItem> cartItemList = cart.getCartItemList();

        //item 받아서 cartItem 만들기
        Item item = itemRepository.findItemById(itemId);
        CartItemDto cartItemDto = CartItemDto.builder()
                .cart(cart)
                .item(item)
                .build();

        //cartItem 만든거 cartItemList 에 넣기
        CartItem cartItem = cartItemRepository.save(cartItemDto.toEntity());

        //만든 리스트 만들어놓은 cart에 add하기
        cartItemList.add(cartItem);*/

        return cart;
    }


}
