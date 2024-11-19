package com.example.toucheese_be.domain.studio.entity.constant;

import lombok.Getter;

// 지역순 필터
@Getter
public enum Region {
    ALL("전체"),
    GANGNAM("강남구"),

    GANGSEO("강서구"),
    GANGBUK("강북구"),

    private final String description;

    Region(String description) {
        this.description = description;
    }
}
