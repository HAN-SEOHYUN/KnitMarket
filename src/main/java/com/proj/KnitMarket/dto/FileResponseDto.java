package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.FileEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FileResponseDto {
    private Long id;
    private String orginFileName;
    private String filePath;

    public FileResponseDto(FileEntity entity){
        this.id=entity.getId();
        this.orginFileName = entity.getOrginFileName();
        this.filePath = entity.getFilePath();
    }

    @Builder
    public FileResponseDto(Long id, String orginFileName, String filePath) {
        this.id = id;
        this.orginFileName = orginFileName;
        this.filePath = filePath;
    }
}
