package com.example.toucheese_be.domain.studio.controller;

import com.example.toucheese_be.domain.studio.dto.ConceptDto;
import com.example.toucheese_be.domain.studio.dto.StudioDto;
import com.example.toucheese_be.domain.studio.dto.StudioSearchFilterDto;
import com.example.toucheese_be.domain.studio.dto.response.StudioDutyDateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "01. [스튜디오] (토큰 필요 X)", description = "스튜디오 관련 API")
public interface StudioApi {
    /**
     * 전체 컨셉 조회
     * @return
     */
    @Operation(summary = "전체 컨셉 조회", description = "모든 컨셉을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "컨셉 조회 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {@ExampleObject(
                                    name = "컨셉 조회 성공",
                                    summary = "컨셉 조회 성공"
                            )}

                    )
            )
    })
    @GetMapping
    ResponseEntity<List<ConceptDto>> getConcepts();

    /**
     * 컨셉 별 스튜디오 조회 (전체 or 조건)
     * @param conceptId
     * @param dto
     * @return
     */
    @Operation(summary = "컨셉 별 스튜디오 조회 (전체 or 조건)", description = """
            <p>컨셉 ID를 통해 해당 컨셉에 속한 스튜디오의 목록을 조회합니다.</p>           
            <p>conceptId : 1 (VIBRANT : 생동감), 2 (FLASHY_GLOSSY : 플래쉬), 3 (BLACK_BLUE_ACTOR : 블랙/블루), 4 (WATERCOLOUR : 수체화), 5 (NATURAL_PHOTO : 내추럴)</p>            
            <p>필터링 조건이 없는경우, page와 size 만을 가지고 모든 스튜디오를 조회합니다.</p>
            <p>필터링 조건이 있을 경우, 해당 조건에 맞는 스튜디오를 조회합니다.</p>                    
            <p>필터링 조건은 StudioSearchFilterDto를 참고하세요.</p>
            """)
    @PostMapping("/search/{conceptId}")
    ResponseEntity<Page<StudioDto>> getStudiosByConceptAndFilters(
            @Parameter(description = "조회할 컨셉 ID", example = "1")
            @PathVariable
            Long conceptId,
            @RequestBody(required = false)
            StudioSearchFilterDto dto
    );

    /**
     * 스튜디오 근무시간/휴무일 조회
     * @param studioId
     * @return
     */
    @Operation(summary = "스튜디오 근무시간/휴무일 조회", description = "특정 스튜디오의 근무시간/휴무일을 조회합니다.")
    @GetMapping("/{studioId}/date")
    ResponseEntity<List<StudioDutyDateDto>> getStudioDutyDate(
            @PathVariable
            Long studioId
    );

}


