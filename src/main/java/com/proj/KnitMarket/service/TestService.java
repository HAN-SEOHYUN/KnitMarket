package com.proj.KnitMarket.service;

import com.proj.KnitMarket.domain.repository.TestRepository;
import com.proj.KnitMarket.dto.TestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;

    public int write(TestDto testDto) {

        return testRepository.save(testDto.toEntity()).getNo();
    }

}

