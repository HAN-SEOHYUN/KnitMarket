package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.Item.FileEntity;
import com.proj.KnitMarket.domain.Item.Item;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileRequestDto {
    private String orginFileName;
    private String fileName;
    private String filePath;
    private Item item; //파일

    public FileEntity toEntity(){
        return FileEntity.builder()
                .orginFileName(orginFileName)
                .fileName(fileName)
                .filePath(filePath)
                .item(item)
                .build();
    }

    @Builder
    public FileRequestDto(String orginFileName, String fileName, String filePath, Item item) {
        this.orginFileName = orginFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.item = item;
    }
}
