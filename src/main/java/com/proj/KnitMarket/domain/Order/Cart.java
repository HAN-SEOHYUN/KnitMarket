package com.proj.KnitMarket.domain.Order;

import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Member.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cart")
@Entity
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<CartItem> cartItemList = new ArrayList<>();

    @Builder
    public Cart(User user,List<CartItem> cartItemList){
        this.user = user;
        this.cartItemList = cartItemList;
    }

}
