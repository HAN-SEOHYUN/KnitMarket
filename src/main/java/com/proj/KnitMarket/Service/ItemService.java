package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.Constant.ConstUtil;
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
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final SellerRepository sellerRepository;
    private final FileEntityRepository fileRepository;
    private final FileService fileService;

    public String uploadDir = ConstUtil.UPLOAD_IMG_PATH; //이미지 저장할 폴더

    //@Transactional : db 트랜잭션 자동으로 commit 해줌
    @Transactional //아이템 등록
    public Long save(ItemRequestDto itemDto, String email,String path) throws IOException {

        Seller seller = sellerRepository.findByEmail(email);

        if (itemDto.getFile() != null) {

            MultipartFile file = itemDto.getFile();
            String filePath =path+ uploadDir + file.getOriginalFilename();
            log.info("2차 path ={}",filePath);

            file.transferTo(new File(filePath));

            FileRequestDto fileDto = FileRequestDto.builder()
                    .orginFileName(file.getOriginalFilename())
                    .filePath(uploadDir + file.getOriginalFilename())
                    .build();

            FileEntity fileEntity = fileService.save(fileDto);
            itemDto.setFileEntity(fileEntity);
            itemDto.setSeller(seller);

        }
        Item item = itemDto.toEntity();
        return itemRepository.save(itemDto.toEntity()).getId();
    }

    // index 상품목록
    @Transactional
    public List<ItemResponseDto> getItemList() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponseDto> itemDtoList = new ArrayList<>();

        for (Item item : items) {
            ItemResponseDto responseDto = ItemResponseDto.builder()
                    .id(item.getId())
                    .itemName(item.getItemName())
                    .itemDesc(item.getItemDesc())
                    .orginFileName(item.getFile().getOrginFileName())
                    .price(item.getPrice())
                    .seller(item.getSeller())
                    .sellStatus(item.getSellStatus())
                    .regTime(item.getRegTime())
                    .build();

            itemDtoList.add(responseDto);
        }//for

        return itemDtoList;
    }

    //상품상세
    @Transactional
    public ItemResponseDto getItemDetail(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .itemDesc(item.getItemDesc())
                .orginFileName(item.getFile().getOrginFileName())
                .price(item.getPrice())
                .seller(item.getSeller())
                .sellStatus(item.getSellStatus())
                .regTime(item.getRegTime())
                .build();

        return itemResponseDto;
    }

    //수정상품조회
    @Transactional
    public ItemRequestDto getUpdateItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ItemRequestDto itemRequestDto = ItemRequestDto.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .itemDesc(item.getItemDesc())
                .price(item.getPrice())
                .seller(item.getSeller())
                .sellStatus(item.getSellStatus())
                .build();

        return itemRequestDto;

    }

    //상품수정
    @Transactional
    public Long updateItem(Long itemId, ItemRequestDto itemRequestDto) throws IOException { //id = null
        Item item = itemRepository.findItemById(itemId);

        if (itemRequestDto.getFile() != null) {
            log.info("이미지 첨부 O");
            MultipartFile file = itemRequestDto.getFile();
            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            FileRequestDto fileRequestDto = FileRequestDto.builder()
                    .orginFileName(file.getOriginalFilename())
                    .filePath(uploadDir + file.getOriginalFilename())
                    .build();

            FileEntity fileEntity = fileService.save(fileRequestDto); // file 저장
            itemRequestDto.setFileEntity(fileEntity);
            item.updateItem(itemRequestDto);
        } else if(itemRequestDto.getFile() == null)  {
            log.info("이미지 첨부 X");
            item.updateItemWithoutFile(itemRequestDto); //이미지첨부 안하면 이전 이미지 그대로 사용
        }
        itemRepository.save(item);
        return item.getId();
    }

    //상품삭제
    @Transactional
    public ItemResponseDto deleteItem(Long id){
        Item item = itemRepository.findItemById(id);
        ItemResponseDto itemResponseDto = getItemDetail(id);

        itemResponseDto.setDeleted(Boolean.TRUE);
        log.info("service 삭제여부={}",itemResponseDto.isDeleted());

        item.deleteItem(itemResponseDto);
        itemRepository.save(item);

        return itemResponseDto;
    }


}
