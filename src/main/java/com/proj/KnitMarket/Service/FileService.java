package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.FileEntity;
import com.proj.KnitMarket.domain.Item.FileEntityRepository;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.dto.FileRequestDto;
import com.proj.KnitMarket.dto.FileResponseDto;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileEntityRepository fileRepository;

    @Transactional
    public FileEntity save(FileRequestDto fileRequestDto){
        return fileRepository.save(fileRequestDto.toEntity());
    }

    @Transactional
    public FileResponseDto getFile(Long id){
        FileEntity fileEntity = fileRepository.findById(id).get();

        FileResponseDto fileResponseDto = FileResponseDto.builder()
                .id(id)
                .orginFileName(fileEntity.getOrginFileName())
                .filePath(fileEntity.getFilePath())
                .build();

        return  fileResponseDto;
    }

}
