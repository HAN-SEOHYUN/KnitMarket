package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.Seller;
import com.proj.KnitMarket.domain.Member.SellerRepository;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final SellerRepository sellerRepository;

    //@Transactional : db 트랜잭션 자동으로 commit 해줌
    @Transactional //아이템 등록
    public Long save(ItemRequestDto itemDto, String email){
        Seller seller = sellerRepository.findByEmail(email);

        itemDto.setSeller(seller);

        Item item = itemDto.toEntity();

        return itemRepository.save(itemDto.toEntity()).getId();
    }

}
