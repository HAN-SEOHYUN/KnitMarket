package com.proj.KnitMarket.dto;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Order.Cart;
import com.proj.KnitMarket.domain.Order.CartItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    private Long id;
    private User user;
    private List<CartItem> cartItemList;

    public Cart toEntity(){
        return Cart.builder()
                .id(id)
                .user(user)
                .cartItemList(cartItemList)
                .build();
    }

    @Builder
    public CartDto(User user, List<CartItem> cartItemList){
        this.user = user;
        this.cartItemList = cartItemList;
    }

}
