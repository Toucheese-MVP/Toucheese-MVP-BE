package com.example.toucheese_be.domain.auth.admin.controller;


import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderDto;
import com.example.toucheese_be.domain.auth.admin.service.AdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    // 관리자 페이지 - 예약 리스트 조회
    @GetMapping
    public ResponseEntity<List<AdminOrderDto>> readAllReservations() {
        return adminService.readAllOrders();
    }

    // 관리자 페이지 - 예약 승인
    @GetMapping("/{reserveId}/approve")
    public void approveReservations(
            @PathVariable
            Long reserveId
    ) {
        adminService.approveReservations(reserveId);
    }

    // 관리자 페이지 - 예약 거절
    @GetMapping("/{reserveId}/reject")
    public void rejectReservations(
            @PathVariable
            Long reserveId
    ) {
        adminService.rejectReservations(reserveId);
    }

}
