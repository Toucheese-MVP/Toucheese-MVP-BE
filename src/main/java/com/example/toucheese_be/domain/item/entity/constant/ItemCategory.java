package com.example.toucheese_be.domain.item.entity.constant;


import lombok.Getter;

@Getter
public enum ItemCategory {
    PROFILE_PHOTO("프로필사진"),
    ID_PHOTO("증명/여권사진"),
    PORTRAIT_PHOTO("화보사진"),
    SEMI_PORTRAIT_PHOTO("세미화보"),
    GROUP_PHOTO("단체사진"),
    UNKNOWN("촬영상품");


    private final String description;

    ItemCategory(String description) {
        this.description = description;
    }
}
