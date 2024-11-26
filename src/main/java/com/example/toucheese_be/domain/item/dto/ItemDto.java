package com.example.toucheese_be.domain.item.dto;

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
}
