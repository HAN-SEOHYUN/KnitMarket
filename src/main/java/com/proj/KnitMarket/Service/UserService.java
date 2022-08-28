package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Member.Address;
import com.proj.KnitMarket.domain.Member.AddressRepository;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.dto.AddressDto;
import com.proj.KnitMarket.dto.UserRequestDto;
import com.proj.KnitMarket.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final HttpSession httpSession;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public User save(UserRequestDto userDto) {
        return userRepository.save(userDto.toEntity());
    }

    @Transactional
    public UserResponseDto findByEmail(String email) {
        User entity = userRepository.findByEmail(email);
        return new UserResponseDto(entity);
    }

    @Transactional
    public UserResponseDto findById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    //주소등록
    @Transactional
    public Long save_address(AddressDto addressDto,Long userId)throws IOException{
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        addressDto.setUser(user);
        return addressRepository.save(addressDto.toEntity()).getId();
    }

    //주소조회
    @Transactional
    public AddressDto getAddress(Long userId){
        Address address = addressRepository.findByUser_Id(userId);
        AddressDto addressDto = new AddressDto();
        if(address!=null){
            addressDto = AddressDto.builder()
                    .id(address.getId())
                    .user(address.getUser())
                    .address(address.getAddress())
                    .addressDetail(address.getAddressDetail())
                    .zipcode(address.getZipcode())
                    .enterMethod(address.getEnterMethod())
                    .build();
        }

        return addressDto;
    }

    @Transactional
    public Long updateAddress(Long addressId, AddressDto addressDto){
        Address address = addressRepository.findById(addressId).orElseThrow(EntityNotFoundException::new);
        Long userId = (Long)httpSession.getAttribute("id");
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        addressDto.setUser(user);
        address.updateAddress(addressDto);
        addressRepository.save(address);
        return address.getId();
    }



}
