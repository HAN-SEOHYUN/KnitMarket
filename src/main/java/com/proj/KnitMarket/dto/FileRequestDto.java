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
    private String filePath;


    public FileEntity toEntity(){
        return FileEntity.builder()
                .orginFileName(orginFileName)
                .filePath(filePath)
                .build();
    }

    @Builder
    public FileRequestDto(String orginFileName, String filePath) {
        this.orginFileName = orginFileName;
        this.filePath = filePath;
    }
}
