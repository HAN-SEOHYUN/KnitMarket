package com.proj.KnitMarket.domain.File;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String originFileName;
    @Column(nullable = false)
    private String fullPath;

    @Builder
    public File(Long id, String originFileName, String fullPath) {
        this.id = id;
        this.originFileName = originFileName;
        this.fullPath = fullPath;
    }
}
