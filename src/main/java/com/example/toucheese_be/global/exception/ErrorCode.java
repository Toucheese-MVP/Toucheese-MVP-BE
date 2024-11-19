package com.example.toucheese_be.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 필터링 관련 에러
    INVALID_FILTER_PARAMETER(400, "FILTER_INVALID_PARAMETER", "필터링 파라미터가 유효하지 않습니다."),
    FILTER_NOT_FOUND(404, "FILTER_NOT_FOUND", "필터 조건에 해당하는 결과가 없습니다."),
    FILTER_PROCESSING_ERROR(500, "FILTER_PROCESSING_ERROR", "필터 처리 중에 오류가 발생했습니다."),

    // 스튜디오 관련 에러
    STUDIO_NOT_FOUND(404, "STUDIO_NOT_FOUND", "해당 스튜디오를 찾을 수 없습니다."),
    STUDIO_DATA_ERROR(500, "STUDIO_DATA_ERROR", "스튜디오 데이터 처리 중 오류가 발생했습니다."),

    // 컨셉 관련 에러
    CONCEPT_NOT_FOUND(404, "CONCEPT_NOT_FOUND", "해당 컨셉을 찾을 수 없습니다."),
    CONCEPT_DATA_ERROR(500, "CONCEPT_DATA_ERROR", "컨셉 데이터 처리 중 오류가 발생했습니다.");

    private final int status;
    private final String code;
    private final String message;
}
