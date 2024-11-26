package com.example.toucheese_be.domain.studio.dto;

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
public class StudioInfoDto {
    private Long studioId;
    private String studioName;
    private String studioProfile;
    private List<String> studioBackground;
    private String popularity;
    private String dutyDate;
    private String address;
    private String studioDescription;
}
