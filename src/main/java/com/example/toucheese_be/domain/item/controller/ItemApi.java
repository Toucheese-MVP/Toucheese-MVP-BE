package com.example.toucheese_be.domain.item.controller;

import com.example.toucheese_be.domain.item.dto.ItemDetailDto;
import com.example.toucheese_be.domain.studio.dto.StudioDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "02. [상품] (토큰 필요 X)", description = "스튜디오 상품 관련 API")
public interface ItemApi {
    /**
     * 스튜디오 상품 조회 - 가격 탭
     */
    @Operation(summary = "스튜디오 상품 조회 (가격탭)", description = "스튜디오의 상품을 조회합니다.")
    @GetMapping("/{studioId}/items")
    ResponseEntity<StudioDetailDto> getStudioItems(
            @PathVariable
            Long studioId
    );

    /**
     * 스튜디오 상품 상세 조회
     */
    @Operation(summary = "스튜디오 상품 상세 조회", description = "스튜디오 상품에 대한 상세정보를 조회합니다.")
    @GetMapping("item/{itemId}/details")
    ResponseEntity<ItemDetailDto> getItemDetails(
            @PathVariable
            Long itemId
    );
}
