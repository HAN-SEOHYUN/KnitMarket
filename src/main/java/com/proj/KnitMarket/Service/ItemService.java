package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public Long save(ItemDto itemDto)throws Exception{
        Item item= itemDto.createItem();
        itemRepository.save(item);
        return item.getId();
    }

}
