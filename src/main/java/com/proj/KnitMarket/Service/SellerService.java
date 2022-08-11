package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Member.Seller;
import com.proj.KnitMarket.domain.Member.SellerRepository;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.dto.SellerRequestDto;
import com.proj.KnitMarket.dto.SellerResponseDto;
import com.proj.KnitMarket.dto.UserRequestDto;
import com.proj.KnitMarket.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    @Transactional //회원등록
    public Long save(SellerRequestDto sellerDto){
        return sellerRepository.save(sellerDto.toEntity()).getId();
    }

    @Transactional
    public SellerResponseDto findByEmail(String email){
        Seller entity= sellerRepository.findByEmail(email);
        return new SellerResponseDto(entity);
    }

    @Transactional
    public boolean existsByEmail(String email){
        return sellerRepository.existsByEmail(email);
    }
}
