package com.example.toucheese_be.domain.review.dto;

import com.example.toucheese_be.domain.item.entity.Item;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImageDto {

    private Long id;
    private String imageUrl;


    public static ReviewImageDto fromEntity(Item item) {
        return ReviewImageDto.builder()
                .id(item.getId())
                .imageUrl(item.getImage())
                .build();

    }
}
