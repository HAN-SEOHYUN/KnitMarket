package com.proj.KnitMarket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    private String searchDataType;
    private String searchBy;
    private String searchQuery;
}
