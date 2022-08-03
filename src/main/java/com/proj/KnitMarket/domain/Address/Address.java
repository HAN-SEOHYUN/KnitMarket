package com.proj.KnitMarket.domain.Address;

import com.proj.KnitMarket.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
public class Address {
    private String addressHead;
    private String addressDetail;
    private int zipcode;
}
