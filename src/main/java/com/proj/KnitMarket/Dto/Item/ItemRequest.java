package com.proj.KnitMarket.Dto.Item;

import com.proj.KnitMarket.domain.User.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class ItemRequest {
    private String itemName;
    private MultipartFile file;
    private User seller;
    private int price;
    private String itemDesc;
    private int sellStatus;
    private LocalDate regTime;
    private Long fileId;


}
