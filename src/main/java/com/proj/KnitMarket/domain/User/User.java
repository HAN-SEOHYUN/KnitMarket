package com.proj.KnitMarket.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

    @Column
    private String ph;
    private String email;
    private String addressId;
    private String accountNo;

    @Builder
    public User(long id, String name, Role role, String ph, String email, String addressId, String accountNo) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.ph = ph;
        this.email = email;
        this.addressId = addressId;
        this.accountNo = accountNo;
    }

    public User update(String name, String ph, String email, String accountNo) {
        this.name = name;
        this.ph = ph;
        this.email = email;
        this.accountNo = accountNo;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
