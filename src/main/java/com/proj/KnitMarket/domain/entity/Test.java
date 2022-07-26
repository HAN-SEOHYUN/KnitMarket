package com.proj.KnitMarket.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private String title;

    private String content;

    @Builder
    public Test(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
