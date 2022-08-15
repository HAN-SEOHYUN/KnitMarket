package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.FileEntity;
import lombok.Getter;

@Getter
public class FileResponseDto {
    private Long id;
    private String orginFileName;
    private String filePath;
    private Long itemId;

    public FileResponseDto(FileEntity entity){
        this.id=entity.getId();
        this.orginFileName = entity.getOrginFileName();
        this.filePath = entity.getFilePath();
        this.itemId=entity.getItem().getId();
    }

}
