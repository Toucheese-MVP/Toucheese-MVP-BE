package com.example.toucheese_be.domain.auth.admin.service;

import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderDto;
import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderItemDto;
import com.example.toucheese_be.domain.auth.admin.dto.AdminOrderOptionDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.entity.OrderItem;
import com.example.toucheese_be.domain.order.entity.constant.OrderStatus;
import com.example.toucheese_be.domain.order.repository.OrderRepository;
import com.example.toucheese_be.domain.studio.dto.PageRequestDto;
import com.example.toucheese_be.global.error.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final OrderRepository orderRepository;
    private final JavaMailSender javaMailSender;

    /**
     * 예약 리스트 조회
     */
    public ResponseEntity<Page<AdminOrderDto>> readAllOrders(PageRequestDto dto) {
         Page<AdminOrderDto> adminOrderDtos = orderRepository.findAllOrdersWithDetails(dto);
         return ResponseEntity.ok(adminOrderDtos);
    }

    /**
     * 예약 승인
     */
    public ResponseEntity<Boolean> approveOrder(Long orderId) {
        // 에약 정보 찾기 로직
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.ORDER_NOT_FOUND));
        // 예약의 상태를 "승인" 으로 변경 후 저장
        OrderStatus orderStatus = order.getStatus();
        if (orderStatus.equals(OrderStatus.KEEP)) {
            // 예약 승인 email 발송
            order.setStatus(OrderStatus.SUCCESS);
            orderRepository.save(order);
            // 사용자 이메일 가져오기
            String userEmail = order.getUser().getEmail();

            // 승인 이메일 발송
            sendEmail(
                    userEmail,
                    "[터치즈] 스튜디오 예약이 승인 되었습니다.",
                    "안녕하세요 터치즈입니다, 고객님. 귀하의 예약이 승인되었습니다. 예약 번호: " + orderId
            );

            return ResponseEntity.ok(true);
        } else if (orderStatus.equals(OrderStatus.SUCCESS)) {
            throw new GlobalCustomException(ErrorCode.ORDER_ALREADY_APPROVED);
        } else {
            throw new GlobalCustomException(ErrorCode.ORDER_ALREADY_REJECTED);
        }
    }


    // TODO: 예약 거절
    public ResponseEntity<Boolean> rejectOrder(Long orderId) {
        // 에약 정보 찾기 로직
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.ORDER_NOT_FOUND));
        // 예약의 상태를 "거절" 으로 변경 후 저장
        OrderStatus orderStatus = order.getStatus();
        if (orderStatus.equals(OrderStatus.KEEP)) {
            // 예약 거절 email 발송
            order.setStatus(OrderStatus.FAIL);
            orderRepository.save(order);
            // 사용자 이메일 가져오기
            String userEmail = order.getUser().getEmail();

            // 승인 이메일 발송
            sendEmail(
                    userEmail,
                    "[터치즈] 스튜디오 예약이 거절 되었습니다.",
                    "안녕하세요 터치즈입니다, 고객님. 귀하의 예약이 거절되었습니다. 예약 번호: " + orderId
            );

            return ResponseEntity.ok(true);
        } else if (orderStatus.equals(OrderStatus.FAIL)) {
            throw new GlobalCustomException(ErrorCode.ORDER_ALREADY_REJECTED);
        } else {
            throw new GlobalCustomException(ErrorCode.ORDER_ALREADY_APPROVED);
        }
    }



    /**
     * 이메일 발송 메서드
     */
    private void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("your-email@example.com"); // 발신 이메일 주소 설정
            javaMailSender.send(message);
        } catch (Exception e) {
            // 이메일 발송 실패 시 로깅
            e.printStackTrace();
        }
    }
}


