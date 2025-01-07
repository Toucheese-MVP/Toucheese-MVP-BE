package com.example.toucheese_be.domain.order.controller;


import com.example.toucheese_be.domain.order.dto.OrderDetailDto;
import com.example.toucheese_be.domain.order.dto.request.OrderRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "04. [예약] (토큰 필요 O)", description = "예약 관련 API")
public interface OrderApi {
    /**
     * 예약 정보 생성
     * @param orderRequestDto
     * @return
     */
    @Operation(summary = "예약 정보 생성", description = "예약 정보를 생성합니다.")
    @PostMapping("/create")
    ResponseEntity<Boolean> getCreateOrder(
            @RequestBody
            OrderRequestDto orderRequestDto
    );


    /**
     * 예약 일정 탭 불러오기
     */
    @Operation(summary = "예약 일정 탭 불러오기", description = "예약 일정 탭 불러오기")
    @GetMapping("/schedule")
    ResponseEntity<List<OrderDetailDto>> getOrderSchedule();


    /**
     * 예약 일정 상세보기
     * @param orderId
     * @return
     */
    @Operation(summary = "예약 일정 상세보기", description = "예약 일정 상세보기")
    @GetMapping("{orderId}/schedule/detailed")
    ResponseEntity<List<OrderDetailDto>> getViewDetailedSchedule(
            @PathVariable
            Long orderId
    );

    /**
     * 예약 일정 수정하기
     * @param orderId
     * @param updatedDto
     * @return
     */
    @Operation(summary = "예약 일정 수정하기", description = "예약 일정 수정하기")
    @PutMapping("/{orderId}/schedule/modify")
    ResponseEntity<Boolean> getModifiedSchedule(
            @PathVariable
            Long orderId,

            @RequestBody
            OrderRequestDto updatedDto
    );

    /**
     * 예약 취소
     * @param orderId
     * @return
     */
    @Operation(summary = "예약 취소하기", description = "예약 취소하기")
    @PutMapping("/{orderId}/cancel")
    ResponseEntity<Boolean> getCancelledSchedule(
            @PathVariable
            Long orderId
    );
}
