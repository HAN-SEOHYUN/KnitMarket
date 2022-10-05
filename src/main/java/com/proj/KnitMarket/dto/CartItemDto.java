package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.Constant.SellStatus;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Order.Cart;
import com.proj.KnitMarket.domain.Order.CartItem;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CartItemDto {

    private Long id;
    private Cart cart;
    private Item item;
    private LocalDateTime regTime;
    private SellStatus sellStatus;

    public CartItem toEntity(){
        return CartItem.builder()
                .id(id)
                .cart(cart)
                .item(item)
                .regTime(regTime)
                .build();
    }

    @Builder
    public CartItemDto(Long id, Cart cart, Item item, LocalDateTime regTime){
        this.id = id;
        this.cart = cart;
        this.item = item;
        this.regTime = regTime;
    }

}
