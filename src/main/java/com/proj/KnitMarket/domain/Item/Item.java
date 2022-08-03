package com.proj.KnitMarket.domain.Item;

import com.proj.KnitMarket.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //상품코드

    @Column(nullable=false)
    private String itemName; //상품명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="seller_id") //seller 테이블의 Id 컬럼과 FK 로 매핑이된다 = 연관관계매핑
    private User seller; // 판매자번호

    @Column(nullable=false)
    private int price; //상품가격

    private String itemDesc; //상품 상세 설명

    private int sellStatus; //상품 판매 상태 (0 : 판매중 / 1 : 품절)

    @Column(nullable=false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate regTime; //상품 등록 시간

    private String imgName; //상품이미지이름
    private String imgPath; //상품이미지경로

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void regTime() {
        this.regTime = LocalDate.now();
    }

}
