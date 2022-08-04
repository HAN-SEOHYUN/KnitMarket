package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.Dto.File.FileDto;
import com.proj.KnitMarket.domain.File.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FileService{

    private final FileRepository fileRepository;

    @Transactional
    public Long save(FileDto fileDto){
        return fileRepository.save(fileDto.toEntity()).getId();
    }

}
