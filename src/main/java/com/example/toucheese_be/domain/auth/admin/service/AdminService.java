package com.example.toucheese_be.domain.auth.admin.service;

import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderDto;
import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderItemDto;
import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderOptionDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.entity.OrderItem;
import com.example.toucheese_be.domain.order.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final OrderRepository orderRepository;

    // 예약 리스트 조회
    public ResponseEntity<List<AdminOrderDto>> readAllOrders() {
         List<AdminOrderDto> adminOrderDtos = orderRepository.findAllOrdersWithDetails();
         return ResponseEntity.ok(adminOrderDtos);
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
