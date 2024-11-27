package com.example.toucheese_be.domain.item.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailDto {
    private ItemInfoDto itemInfoDto;
    private List<OptionDto> optionDtoList;
}
