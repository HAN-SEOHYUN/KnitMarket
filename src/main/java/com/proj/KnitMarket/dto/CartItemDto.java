package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Order.Cart;
import com.proj.KnitMarket.domain.Order.CartItem;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {

    private Long id;
    private Cart cart;
    private Item item;

    public CartItem toEntity(){
        return CartItem.builder()
                .id(id)
                .cart(cart)
                .item(item)
                .build();
    }

    @Builder
    public CartItemDto(Cart cart, Item item){
        this.cart = cart;
        this.item = item;
    }

}
