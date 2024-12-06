package com.example.toucheese_be.domain.auth.admin.service;

import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final OrderRepository orderRepository;


    // 예약 리스트 조회
    public ResponseEntity<List<AdminOrderDto>> readAllOrders() {
        // 모든 예약 정보 찾기 로직
        List<Order> orderList = orderRepository.findAll();

        // dto 로 변환 후 반환

    }


    // 예약 승인
    public void approveOrder(Long reserveId) {
        // 에약 정보 찾기 로직

        // 예약의 상태를 "승인" 으로 변경 후 저장

        // 반환 ture or false
    }


    // 예약 거절
    public void rejectOrder(Long reserveId) {
        // 에약 정보 찾기 로직

        // 예약 상태를 "거절" 로 변경 후 저장

        // 반환 true or false
    }
}
