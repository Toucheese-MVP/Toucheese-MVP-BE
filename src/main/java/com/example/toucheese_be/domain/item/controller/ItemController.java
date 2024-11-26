package com.example.toucheese_be.domain.item.controller;

import com.example.toucheese_be.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/details/{itemId}")
    public void getItemDetails() {}



}
