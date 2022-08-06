package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional //db 트랜잭션 자동으로 commit 해줌
    public Long save(ItemDto itemDto){
        return itemRepository.save(itemDto.toEntity()).getId();
    }

}
