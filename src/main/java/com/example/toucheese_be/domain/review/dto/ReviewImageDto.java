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
                // 상품의 리뷰 id, 리뷰 이미지 필요
                .id(item.getId()) //-> 리뷰 ID
                .imageUrl(item.getImage()) // -> 리뷰 이미지 (여러개중 첫번쨰 사진)
                .build();

    }
}
