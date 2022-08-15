package com.proj.KnitMarket.domain.Item;

import com.proj.KnitMarket.domain.Member.Seller;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="file")
@Entity
public class FileEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orginFileName;

    private String filePath;

    @OneToOne
    @JoinColumn(name="itme_id")
    private Item item; //파일*/

    @Builder
    public FileEntity(Long id, String orginFileName, String filePath,Item item) {
        this.id = id;
        this.orginFileName = orginFileName;
        this.filePath = filePath;
        this.item = item;
    }
}
