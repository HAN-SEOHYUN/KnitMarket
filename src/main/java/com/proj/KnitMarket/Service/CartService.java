package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.domain.Order.Cart;
import com.proj.KnitMarket.domain.Order.CartItem;
import com.proj.KnitMarket.domain.Order.CartItemRepository;
import com.proj.KnitMarket.domain.Order.CartRepository;
import com.proj.KnitMarket.dto.CartItemDto;
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
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    //회원번호와 아이템번호 넣으면 장바구니에 상품을 추가해주는 메서드
    @Transactional
    public boolean save(Long userId, Long itemId) {
        log.info("cartService");
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Item item = itemRepository.findItemById(itemId);

        Cart cart = cartRepository.findCartByUser_Id(userId);
        log.info("cartId={}", cart.getId());

        boolean isItemExist = cartItemRepository.existsCartItemByCart_IdAndItem_Id(cart.getId(), itemId);

        log.info("isItemExist={}",isItemExist);
        boolean addSuccess;

        if (!isItemExist) { //장바구니에 해당 상품이 없으면 추가
            CartItemDto cartItemDto = CartItemDto.builder()
                    .cart(cart)
                    .item(item)
                    .build();

            CartItem cartItem = cartItemRepository.save(cartItemDto.toEntity());

            List<CartItem> cartItemList = cartItemRepository.findByCart_Id(cart.getId());

            cartItemList.add(cartItem);

            addSuccess = true;
        } else { // 이미존재하면 false 반환
            addSuccess = false;
        }
        return addSuccess;
    }

    @Transactional
    public List<CartItemDto> getCartItemList(Long userId){
        Long cartId = cartRepository.findCartByUser_Id(userId).getId();
        List<CartItem> cartItems = cartItemRepository.findByCart_Id(cartId);
        List<CartItemDto> cartItemDtoList = new ArrayList<>();

        for(CartItem cartItem : cartItems){
            CartItemDto cartItemDto = CartItemDto.builder()
                    .id(cartItem.getId())
                    .cart(cartItem.getCart())
                    .item(cartItem.getItem())
                    .regTime(cartItem.getRegTime())
                    .build();

            cartItemDtoList.add(cartItemDto);
        }
        return cartItemDtoList;
    }

    @Transactional
    public void cartRemove(Long cartItemId){
        cartItemRepository.deleteById(cartItemId);
    }
}
