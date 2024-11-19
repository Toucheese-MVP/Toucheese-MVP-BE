package com.example.toucheese_be.domain.studio.entity.constant;

import lombok.Getter;

// 지역순 필터
@Getter
public enum Region {
    ALL("전체"),
    GANGNAM("강남/서초/송파"),
    GANGSEO("강서/마포/영등포"),
    GANGBUK("강북/용산/성동");

    private final String description;

    Region(String description) {
        this.description = description;
    }
}
