package com.example.toucheese_be.domain.studio.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDto {
    @Builder.Default
    private int page = 0;
    @Builder.Default
    private int size = 10;
    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}
