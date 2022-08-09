package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Member.MemberRepository;
import com.proj.KnitMarket.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional //회원등록
    public Long save(MemberRequestDto memberDto){
        return memberRepository.save(memberDto.toEntity()).getId();
    }

}
