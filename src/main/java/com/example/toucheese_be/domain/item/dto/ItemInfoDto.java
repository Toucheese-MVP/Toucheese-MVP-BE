package com.example.toucheese_be.domain.item.dto;

import com.example.toucheese_be.domain.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoDto {
    private Long itemId;
    private String itemImage;
    private String itemName;
    private String itemDescription;
    private Integer itemPrice;

    public static ItemInfoDto fromEntity(Item entity) {
        return ItemInfoDto.builder()
                .itemId(entity.getId())
                .itemImage(entity.getImage())
                .itemName(entity.getName())
                .itemDescription(entity.getDescription())
                .itemPrice(entity.getPrice())
                .build();
    }
}
