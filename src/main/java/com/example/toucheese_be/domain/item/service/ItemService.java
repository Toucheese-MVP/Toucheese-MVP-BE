package com.example.toucheese_be.domain.item.service;

import com.example.toucheese_be.domain.item.dto.ItemDetailDto;
import com.example.toucheese_be.domain.item.dto.ItemInfoDto;
import com.example.toucheese_be.domain.item.dto.OptionDto;
import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.item.entity.ItemOption;
import com.example.toucheese_be.domain.item.entity.Option;
import com.example.toucheese_be.domain.item.repository.ItemRepository;
import com.example.toucheese_be.domain.item.repository.ItemOptionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemOptionRepository itemOptionRepository;

    public ResponseEntity<ItemDetailDto> getItemDetails(Long itemId) {
        // 상품 조회
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("아이템을 찾을 수 없습니다."));

        // ItemInfoDto 변환
        ItemInfoDto itemInfoDto = ItemInfoDto.fromEntity(item);

        // 상품에 해당하는 추가옵션들을 가져옴
        List<ItemOption> itemOptions = itemOptionRepository.findByItemId(itemId);

        List<OptionDto> optionDtos = itemOptions.stream()
                .map(itemOption -> OptionDto.fromEntity(itemOption.getOption()))
                .toList();

        ItemDetailDto itemDetailDto = ItemDetailDto.builder()
                .itemInfoDto(itemInfoDto)
                .optionDtoList(optionDtos)
                .build();

        return ResponseEntity.ok(itemDetailDto);
    }
}
