package com.example.toucheese_be.domain.user.fcm;


import com.example.toucheese_be.domain.user.entity.User;
import com.example.toucheese_be.domain.user.repository.UserRepository;
import com.example.toucheese_be.global.error.ErrorCode;
import com.example.toucheese_be.global.error.GlobalCustomException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FcmNotificationService {
    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository userRepository;

    public String sendNotificationByToken(Long targetUserId, String title, String body) {
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
            return "알림을 성공적으로 전송했습니다. targetUserId=" + targetUserId;
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "알림 보내기를 실패하였습니다. targetuserId=" + targetUserId;
        }
    }
}
