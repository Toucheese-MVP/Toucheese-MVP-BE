package com.example.toucheese_be.domain.user.service;

import com.example.toucheese_be.domain.user.dto.response.AdminOrderDto;
import com.example.toucheese_be.domain.order.entity.Order;
import com.example.toucheese_be.domain.order.entity.constant.OrderStatus;
import com.example.toucheese_be.domain.order.repository.OrderRepository;
import com.example.toucheese_be.domain.studio.dto.PageRequestDto;
import com.example.toucheese_be.domain.user.entity.User;
import com.example.toucheese_be.domain.user.repository.UserRepository;
import com.example.toucheese_be.global.error.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FirebaseMessaging firebaseMessaging;
    public JavaMailSender javaMailSender;


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
        if (orderStatus.equals(OrderStatus.KEEP_RESERVATION)) {
            // 예약 승인 email 발송
            order.setStatus(OrderStatus.CONFIRM_RESERVATION);
            orderRepository.save(order);
            // 사용자 이메일 가져오기
            User user = order.getUser();
            String subject = "[터치즈] 스튜디오 예약이 승인 되었습니다.";
            String text = "안녕하세요 터치즈입니다, 고객님. 귀하의 예약이 승인되었습니다. 예약 번호: " + orderId;

            // 승인 이메일 발송
            sendEmail(user.getEmail(), subject, text);
            // FCM 발송
            sendNotificationByToken(user.getId(), subject, text);


            return ResponseEntity.ok(true);
        } else if (orderStatus.equals(OrderStatus.CONFIRM_RESERVATION)) {
            throw new GlobalCustomException(ErrorCode.ORDER_ALREADY_APPROVED);
        } else {
            throw new GlobalCustomException(ErrorCode.ORDER_ALREADY_REJECTED);
        }
    }


    /**
     * 예약 거절
     */
    public ResponseEntity<Boolean> rejectOrder(Long orderId) {
        // 에약 정보 찾기 로직
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.ORDER_NOT_FOUND));
        // 예약의 상태를 "거절" 으로 변경 후 저장
        OrderStatus orderStatus = order.getStatus();
        if (orderStatus.equals(OrderStatus.KEEP_RESERVATION)) {
            // 예약 거절 email 발송
            order.setStatus(OrderStatus.CANCEL_RESERVATION);
            orderRepository.save(order);
            // 사용자 이메일 가져오기
            User user = order.getUser();
            String subject = "[터치즈] 스튜디오 예약이 거절 되었습니다.";
            String text = "안녕하세요 터치즈입니다, 고객님. 귀하의 예약이 거절되었습니다. 예약 번호: " + orderId;

            // 승인 이메일 발송
            sendEmail(user.getEmail(), subject, text);
            // FCM 발송
            sendNotificationByToken(user.getId(), subject, text);

            return ResponseEntity.ok(true);
        } else if (orderStatus.equals(OrderStatus.CANCEL_RESERVATION)) {
            throw new GlobalCustomException(ErrorCode.ORDER_ALREADY_REJECTED);
        } else {
            throw new GlobalCustomException(ErrorCode.ORDER_ALREADY_APPROVED);
        }
    }

    /**
     * 촬영 완료
     */
    public ResponseEntity<Boolean> finishOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.ORDER_NOT_FOUND));
        OrderStatus orderStatus = order.getStatus();
        if (orderStatus.equals(OrderStatus.CONFIRM_RESERVATION)) {
            order.setStatus(OrderStatus.FINISHED_FILM);
            orderRepository.save(order);
            User user = order.getUser();
            String subject = "[터치즈] 스튜디오 촬영이 완료되었습니다.";
            String text = "안녕하세요 터치즈입니다, 고객님. 귀하의 촬영이 완료되었습니다. 예약 번호: " + orderId;

            // 승인 이메일 발송
            sendEmail(user.getEmail(), subject, text);
            // FCM 발송
            sendNotificationByToken(user.getId(), subject, text);

            return ResponseEntity.ok(true);
        } else if (orderStatus.equals(OrderStatus.FINISHED_FILM)){
            throw new GlobalCustomException(ErrorCode.ORDER_ALREADY_FINISH);
        } else {
            throw new GlobalCustomException(ErrorCode.ORDER_CANT_FINISH);
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

    /**
     * FCM 발송
     */
    private void sendNotificationByToken(Long targetUserId, String title, String body) {
        User user = userRepository.findById(targetUserId)
                .orElseThrow(() -> new GlobalCustomException(ErrorCode.USER_NOT_FOUND));

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setToken(user.getFirebaseToken())
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
            log.info("알림을 성공적으로 전송했습니다. targetUserId=" + targetUserId);
        } catch (FirebaseMessagingException e) {
            log.info("알림 보내기를 실패하였습니다. targetuserId=" + targetUserId);
            throw new GlobalCustomException(ErrorCode.FCM_SERVICE_UNAVAILABLE);
        }
    }
}


