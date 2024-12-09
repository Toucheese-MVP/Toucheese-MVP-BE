package com.example.toucheese_be.domain.auth.admin.controller;


import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderDto;
import com.example.toucheese_be.domain.auth.admin.service.AdminService;
import com.example.toucheese_be.domain.studio.dto.PageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // 관리자 페이지 - 예약 승인
    @GetMapping("/{orderId}/approve")
    public void approveOrder(
            @PathVariable
            Long orderId
    ) {
        adminService.approveOrder(orderId);
    }

    // 관리자 페이지 - 예약 거절
    @GetMapping("/{orderId}/reject")
    public void rejectOrder(
            @PathVariable
            Long orderId
    ) {
        adminService.rejectOrder(orderId);
    }

}
