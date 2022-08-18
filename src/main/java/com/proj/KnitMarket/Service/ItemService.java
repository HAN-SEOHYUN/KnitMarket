package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.FileEntity;
import com.proj.KnitMarket.domain.Item.FileEntityRepository;
import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.Seller;
import com.proj.KnitMarket.domain.Member.SellerRepository;
import com.proj.KnitMarket.dto.FileRequestDto;
import com.proj.KnitMarket.dto.ItemRequestDto;
import com.proj.KnitMarket.dto.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    public List<ItemResponseDto> getItemList(){
        List<Item> items = itemRepository.findAll();
        List<ItemResponseDto> itemDtoList = new ArrayList<>();

        for(Item item : items){
            ItemResponseDto responseDto = ItemResponseDto.builder()
                    .id(item.getId())
                    .itemName(item.getItemName())
                    .itemDesc(item.getItemDesc())
                    .orginFileName(item.getFile().getOrginFileName())
                    .price(item.getPrice())
                    .sellerName(item.getSeller().getName())
                    .sellStatus(item.getSellStatus())
                    .regTime(item.getRegTime())
                    .build();

            itemDtoList.add(responseDto);
        }//for

        return itemDtoList;
    }

    //상품상세
    @Transactional
    public ItemResponseDto getItemDetail(Long id){
        Item item = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .itemDesc(item.getItemDesc())
                .orginFileName(item.getFile().getOrginFileName())
                .price(item.getPrice())
                .sellerName(item.getSeller().getName())
                .sellStatus(item.getSellStatus())
                .regTime(item.getRegTime())
                .build();

        return itemResponseDto;


    }



}
