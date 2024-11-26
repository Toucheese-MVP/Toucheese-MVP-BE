package com.example.toucheese_be.domain.item.dto;

import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.item.entity.constant.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String itemName;
    private String itemDescription;
    private Integer reviewCounts;
    private Integer price;

    public static ItemDto fromEntity(Item entity) {
        return ItemDto.builder()
                .itemName(entity.getName())
                .itemDescription(entity.getDescription())
                .reviewCounts(null)
                .price(entity.getPrice())
                .build();
    }
}
