package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.FileEntity;
import com.proj.KnitMarket.domain.Item.FileEntityRepository;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.Seller;
import com.proj.KnitMarket.domain.Member.SellerRepository;
import com.proj.KnitMarket.dto.FileRequestDto;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final SellerRepository sellerRepository;
    private final FileEntityRepository fileRepository;
    private final FileService fileService;

    //@Transactional : db 트랜잭션 자동으로 commit 해줌
    @Transactional //아이템 등록
    public Long save(ItemRequestDto itemDto, String email, FileRequestDto fileRequestDto){
        Seller seller = sellerRepository.findByEmail(email);

        FileEntity file = fileService.save(fileRequestDto); // file 저장

        itemDto.setFileEntity(file);
        itemDto.setSeller(seller);

        Item item = itemDto.toEntity();

        return itemRepository.save(itemDto.toEntity()).getId();
    }

    // index 상품목록

}
