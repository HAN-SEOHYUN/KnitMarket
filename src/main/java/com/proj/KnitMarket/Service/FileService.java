package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.FileEntityRepository;
import com.proj.KnitMarket.dto.FileRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileEntityRepository fileRepository;

    @Transactional
    public Long save(FileRequestDto fileRequestDto){
        return fileRepository.save(fileRequestDto.toEntity()).getId();
    }

}
