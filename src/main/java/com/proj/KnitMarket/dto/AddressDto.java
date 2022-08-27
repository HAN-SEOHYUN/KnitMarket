package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Member.Address;
import com.proj.KnitMarket.domain.Member.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {

    private Long id;
    private User user;
    private String address;
    private String addressDetail;
    private String zipcode;
    private String enterMethod;

    public Address toEntity(){
        return Address.builder()
                .user(user)
                .address(address)
                .addressDetail(addressDetail)
                .zipcode(zipcode)
                .enterMethod(enterMethod)
                .build();
    }

    @Builder
    public AddressDto(Long id, User user, String address, String addressDetail, String zipcode, String enterMethod) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
        this.enterMethod = enterMethod;
    }
}
