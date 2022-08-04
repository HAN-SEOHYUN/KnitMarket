package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.Dto.Item.ItemDto;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long save(ItemDto itemDto) {
        return itemRepository.save(itemDto.toEntity()).getId();
    }

}
