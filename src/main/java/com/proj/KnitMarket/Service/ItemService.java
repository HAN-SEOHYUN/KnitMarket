package com.proj.KnitMarket.Service;

import com.proj.KnitMarket.domain.Item.ItemRepository;
import com.proj.KnitMarket.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    //@Transactional : db 트랜잭션 자동으로 commit 해줌
    @Transactional //아이템 등록
    public Long save(final ItemRequestDto itemDto){
        return itemRepository.save(itemDto.toEntity()).getId();
    }

    //아이템 수정
/*    @Transactional
    public Long update(final Long id, final BoardRequestDto params) {

        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;
    }*/


}
