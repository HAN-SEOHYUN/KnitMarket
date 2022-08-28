package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.Item;
import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.domain.Member.Seller;
import com.proj.KnitMarket.domain.Member.SellerRepository;
import com.proj.KnitMarket.dto.ItemResponseDto;
import com.proj.KnitMarket.dto.SellerRequestDto;
import com.proj.KnitMarket.dto.SellerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final ItemRepository itemRepository;
    private final HttpSession httpSession;

    @Transactional //회원등록
    public Long save(SellerRequestDto sellerDto) {
        return sellerRepository.save(sellerDto.toEntity()).getId();
    }

    @Transactional
    public SellerResponseDto findByEmail(String email) {
        Seller entity = sellerRepository.findByEmail(email);
        return new SellerResponseDto(entity);
    }

    @Transactional
    public SellerResponseDto findById(Long sellerId){
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(EntityNotFoundException::new);
        return new SellerResponseDto(seller);
    }

    @Transactional
    public boolean existsByEmail(String email) {
        return sellerRepository.existsByEmail(email);
    }

    //판매자가 게시한 상품 조회 (삭제된 상품 제외)
    @Transactional
    public List<ItemResponseDto> getMyItemList(Long sellerId) {
        List<Item> itemLists = itemRepository.findBySeller_Id(sellerId);
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

        for (Item item : itemLists) {
            ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                    .id(item.getId())
                    .itemName(item.getItemName())
                    .price(item.getPrice())
                    .regTime(item.getRegTime())
                    .sellStatus(item.getSellStatus())
                    .orginFileName(item.getFile().getOrginFileName())
                    .build();
            itemResponseDtoList.add(itemResponseDto);
        }
        return itemResponseDtoList;
    }


}
