package com.proj.KnitMarket.domain.Member;

import com.proj.KnitMarket.domain.BaseEntity;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.dto.AddressDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Seller extends BaseEntity {

    @Id
    @Column(name="seller_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // 회원번호

    @Column(nullable = false)
    private String name; //이름

    @Column
    private String email; //이메일

    @Column
    private String store; //가게이름

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Item> item = new ArrayList<>(); // 판매자가 판매중인 상품들

    @Builder
    public Seller(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void updateStore(String store){
        this.store = store;
    }
}
