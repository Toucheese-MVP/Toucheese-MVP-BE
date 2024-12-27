package com.example.toucheese_be.domain.user.controller;


import com.example.toucheese_be.domain.user.fcm.FcmNotificationRequestDto;
import com.example.toucheese_be.domain.user.service.AdminService;
import com.example.toucheese_be.domain.user.dto.response.AdminOrderDto;
import com.example.toucheese_be.domain.studio.dto.PageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    /**
     * 관리자 페이지 - 예약 리스트 조회
     */
    @PostMapping
    public ResponseEntity<Page<AdminOrderDto>> readAllOrders(
            @RequestBody
            PageRequestDto dto
    ) {
        return adminService.readAllOrders(dto);
    }

    /**
     * 관리자 페이지 - 예약 승인
     */
    @PutMapping("/{orderId}/approve")
    public ResponseEntity<Boolean> approveOrder(
            @PathVariable
            Long orderId
    ) {
        return adminService.approveOrder(orderId);
    }

    /**
     * 관리자 페이지 - 예약 거절
     */
    @PutMapping("/{orderId}/reject")
    public ResponseEntity<Boolean> rejectOrder(
            @PathVariable
            Long orderId
    ) {
        return adminService.rejectOrder(orderId);
    }

    /**
     * 관리자 페이지 - 촬영 완료
     */
    @PutMapping("/{orderId}/finish")
    public ResponseEntity<Boolean> finishOrder(
            @PathVariable
            Long orderId
    ) {
        return adminService.finishOrder(orderId);
    }

}
