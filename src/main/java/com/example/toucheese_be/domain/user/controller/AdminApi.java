package com.example.toucheese_be.domain.user.controller;

import com.example.toucheese_be.domain.studio.dto.PageRequestDto;
import com.example.toucheese_be.domain.user.dto.response.AdminOrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "05. [관리자] (토큰 필요 O)", description = "관리자 관련 API")
public interface AdminApi {
    /**
     * 관리자 페이지 - 예약 리스트 조회
     */
    @Operation(summary = "관리자 페이지 - 예약 리스트 조회", description = "관리자 페이지에서 예약 리스트를 조회합니다.")
    @PostMapping
    ResponseEntity<Page<AdminOrderDto>> readAllOrders(
            @RequestBody
            PageRequestDto dto
    );

    /**
     * 관리자 페이지 - 예약 승인
     */
    @Operation(summary = "관리자 페이지 - 예약 승인", description = "관리자 페이지에서 예약을 승인합니다.")
    @PutMapping("/{orderId}/approve")
    ResponseEntity<Boolean> approveOrder(
            @PathVariable
            Long orderId
    );

    /**
     * 관리자 페이지 - 예약 거절
     */
    @Operation(summary = "관리자 페이지 - 예약 거절", description = "관리자 페이지에서 예약을 거절합니다.")
    @PutMapping("/{orderId}/reject")
    ResponseEntity<Boolean> rejectOrder(
            @PathVariable
            Long orderId
    );

    /**
     * 관리자 페이지 - 촬영 완료
     */
    @Operation(summary = "관리자 페이지 - 촬영 완료", description = "관리자 페이지에서 촬영을 완료합니다.")
    @PutMapping("/{orderId}/finish")
    ResponseEntity<Boolean> finishOrder(
            @PathVariable
            Long orderId
    );
}
