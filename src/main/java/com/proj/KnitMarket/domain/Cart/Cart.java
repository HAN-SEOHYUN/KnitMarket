package com.proj.KnitMarket.domain.Cart;

import com.proj.KnitMarket.domain.CartItem.CartItem;
import com.proj.KnitMarket.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 장바구니 번호

    @OneToOne(fetch = FetchType.LAZY) // lazy = 지연로딩 => 조인하지 않고 멤버만 가져오고싶을 때
    private User user; // 장바구니 소유자

    private int count; // 장바구니에 담긴 총 상품 개수

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>(); // 장바구니에 담긴 상품 목록







}
