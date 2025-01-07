package com.example.toucheese_be.domain.item.controller;

import com.example.toucheese_be.domain.item.dto.ItemDetailDto;
import com.example.toucheese_be.domain.item.service.ItemService;
import com.example.toucheese_be.domain.studio.dto.StudioDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/studio")
@RequiredArgsConstructor
public class ItemController implements ItemApi{
    private final ItemService itemService;


    /**
     * 스튜디오 상세 조회 - 가격 탭
     */
    @GetMapping("/{studioId}/items")
    public ResponseEntity<StudioDetailDto> getStudioItems(
            @PathVariable
            Long studioId
    ) {
        return itemService.getStudioItems(studioId);
    }

    /**
     * 스튜디오 상품 상세 조회
     */
    @GetMapping("item/{itemId}/details")
    public ResponseEntity<ItemDetailDto> getItemDetails(
            @PathVariable
            Long itemId
    ) {
        return itemService.getItemDetails(itemId);
    }
}
