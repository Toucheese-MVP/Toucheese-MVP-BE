package com.example.toucheese_be.domain.user.controller;


import com.example.toucheese_be.domain.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    // 관리자 페이지 - 조회
    @GetMapping
    public void readAllReservations() {
        adminService.readAllReservations();
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
