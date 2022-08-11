package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Member.UserRepository;
import com.proj.KnitMarket.dto.UserRequestDto;
import com.proj.KnitMarket.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional //회원등록
    public Long save(UserRequestDto userDto){
        return userRepository.save(userDto.toEntity()).getId();
    }

    @Transactional
    public UserResponseDto findByEmail(String email){
         User entity= userRepository.findByEmail(email);
        return new UserResponseDto(entity);
    }

    @Transactional
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
