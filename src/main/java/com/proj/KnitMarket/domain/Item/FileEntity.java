package com.proj.KnitMarket.domain.Item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FileEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orginFileName;

    private String filePath;

    @Builder
    public FileEntity(Long id, String orginFileName, String filePath) {
        this.id = id;
        this.orginFileName = orginFileName;
        this.filePath = filePath;
    }
}
