package com.example.toucheese_be.domain.item.service;

import com.example.toucheese_be.domain.item.dto.ItemDetailDto;
import com.example.toucheese_be.domain.item.dto.ItemDto;
import com.example.toucheese_be.domain.item.dto.ItemInfoDto;
import com.example.toucheese_be.domain.item.dto.OptionDto;
import com.example.toucheese_be.domain.item.entity.Item;
import com.example.toucheese_be.domain.item.entity.ItemOption;
import com.example.toucheese_be.domain.item.entity.constant.ItemCategory;
import com.example.toucheese_be.domain.item.repository.ItemRepository;
import com.example.toucheese_be.domain.item.repository.ItemOptionRepository;
import com.example.toucheese_be.domain.studio.dto.StudioDetailDto;
import com.example.toucheese_be.domain.studio.dto.StudioInfoDto;
import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.repository.StudioRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final StudioRepository studioRepository;
    private final ItemOptionRepository itemOptionRepository;

    // 스튜디오 상세 페이지 조회 - 가격탭
    public ResponseEntity<StudioDetailDto> getStudioItems(Long studioId) {
        // 스튜디오 조회
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new IllegalArgumentException("스튜디오를 찾을 수 없습니다."));

        // StudioInfoDto 변환
        StudioInfoDto studioInfoDto = StudioInfoDto.fromEntity(studio);

        // 스튜디오에 해당하는 아이템들을 가져옴
        List<Item> items = itemRepository.findByStudioId(studioId);

        // ItemCategory별로 아이템을 그룹화 (null은 "촬영상품"으로 처리)
        Map<ItemCategory, List<ItemDto>> categorizedItems = items.stream()
                .collect(Collectors.groupingBy(
                        item -> item.getItemCategory() != null ? item.getItemCategory() : ItemCategory.UNKNOWN, // null이면 UNKNOWN
                        Collectors.mapping(ItemDto::fromEntity, Collectors.toList())
                ));

        // StudioDetailDto 반환
        StudioDetailDto studioDetailDto = StudioDetailDto.builder()
                .studioInfoDto(studioInfoDto)  // studioInfoDto는 StudioInfoDto.fromEntity()에서 변환됨
                .categorizedItems(categorizedItems)  // 아이템을 ItemCategory별로 그룹화한 Map
                .build();

        return ResponseEntity.ok(studioDetailDto);
    }

    // 스튜디오 상품 상세 조회
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
