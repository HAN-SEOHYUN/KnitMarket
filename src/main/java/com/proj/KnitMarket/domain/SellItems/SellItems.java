package com.proj.KnitMarket.domain.SellItems;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.OrderItems.OrderItems;
import com.proj.KnitMarket.domain.Sell.Sell;
import com.proj.KnitMarket.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class SellItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sell_id")
    private Sell sell;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @OneToOne(mappedBy = "sellItems")
    private OrderItems orderItems;

    private int isCancel;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate sellDate;





}
