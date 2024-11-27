package com.example.toucheese_be.domain.item.controller;

import com.example.toucheese_be.domain.item.dto.ItemDetailDto;
import com.example.toucheese_be.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;


    /**
     * TODO: 스튜디오 상품 상세 조회
     */
    @GetMapping("{itemId}/details")
    public ResponseEntity<ItemDetailDto> getItemDetails(
            @PathVariable
            Long itemId
    ) {
        return itemService.getItemDetails(itemId);
    }
}
