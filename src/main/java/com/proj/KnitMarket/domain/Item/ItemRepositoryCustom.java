package com.proj.KnitMarket.domain.Item;

import com.proj.KnitMarket.dto.ItemListDto;
import com.proj.KnitMarket.dto.ItemSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    //@QueryProjection 을 이용하여 바로 Dto 객체 반환
    Page<ItemListDto> getIndexPage(ItemSearchDto itemSearchDto, Pageable pageable);

}
