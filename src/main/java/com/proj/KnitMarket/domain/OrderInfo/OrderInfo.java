package com.proj.KnitMarket.domain.OrderInfo;


import com.proj.KnitMarket.domain.OrderItems.OrderItems;
import com.proj.KnitMarket.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class OrderInfo { // 구매자의 주문정보

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문번호

    @ManyToOne(fetch = FetchType.LAZY) //한개의 유저는 여러개의 주문정보를 가질 수 있음
    @JoinColumn(name ="user_id")
    private User user; // 구매자

    @OneToMany(mappedBy = "order")
    private List<OrderItems> OrderItems = new ArrayList<>(); // 주문상세정보

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate OrderInfoDate; // 주문한 날짜



}
