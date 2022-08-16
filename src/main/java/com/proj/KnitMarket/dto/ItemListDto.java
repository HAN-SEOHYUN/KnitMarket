package com.proj.KnitMarket.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemListDto {

    private Long id;
    private String itemName;
    private String itemDesc;
    private int price;
    private String filePath;

    @QueryProjection //@QueryProjection은 생성자를 통해 DTO를 조회하는 방법과 함께 사용됩니다 => 이후 빌드 시 DTO가 Q파일로 생성됩니다.
    public ItemListDto(Long id, String itemName, String itemDesc, int price, String filePath) {
        this.id = id;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.price = price;
        this.filePath = filePath;
    }
}
