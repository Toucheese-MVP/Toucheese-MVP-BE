package com.example.toucheese_be.domain.reserve.dto;

import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.reserve.entity.ReserveInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ReservedItemDto {
    private Long id;
    private String itemImage;
    private String studioName;
    private int itemPrice;

//    public static ReservedItemDto fromEntity(Item entity){
//        String itemImage = entity.getImage();
//    }
}