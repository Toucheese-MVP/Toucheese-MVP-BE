package com.example.toucheese_be.domain.studio.dto;

import com.example.toucheese_be.domain.item.dto.ItemDto;
import com.example.toucheese_be.domain.item.entity.constant.ItemCategory;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudioDetailDto {
    private StudioInfoDto studioInfoDto;
    private Map<ItemCategory, List<ItemDto>> categorizedItems;
}
