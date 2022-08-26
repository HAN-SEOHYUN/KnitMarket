package com.proj.KnitMarket.domain.Member;

import com.proj.KnitMarket.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Address extends BaseEntity {

    @Id
    @Column(name="address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private User user;

    private String address;
    private String addressDetail;
    private String zipcode;
    private String enterMethod;

    @Builder
    public Address(User user, String address, String addressDetail, String zipcode, String enterMethod) {
        this.user = user;
        this.address = address;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
        this.enterMethod = enterMethod;
    }
}
