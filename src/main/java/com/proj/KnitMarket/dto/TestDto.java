package com.proj.KnitMarket.dto;

import com.proj.KnitMarket.domain.entity.Test;
import lombok.*;

@ToString
@Data
public class TestDto {

    public String title;
    public String content;

    @Builder
    public TestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Test toEntity() {
        return Test.builder()
                .title(title)
                .content(content)
                .build();
    }
}
