//package com.example.toucheese_be.domain.user.fcm;
//
//import com.google.firebase.messaging.MulticastMessage;
//import com.google.firebase.messaging.Notification;
//import java.util.List;
//import lombok.Builder;
//import lombok.NonNull;정
//
//@Builder
//public record NotificationMulticastRequest(
//        @NonNull String targetTokens,
//        String title,
//        String body
//) implements NotificationRequest {
//    public static NotificationMulticastRequest of(List<String> tokens, String title, String body) {
//        return NotificationMulticastRequest.builder()
//                .targetTokens(tokens)
//                .title(title)
//                .body(body)
//                .build();
//    }
//
//    public MulticastMessage.Builder buildSendMessage() {
//        return MulticastMessage.builder()
//                .setNotification(toNotification())
//                .addAllTokens(targetTokens);
//    }
//
//    public Notification toNotification() {
//        return Notification.builder()
//                .setTitle(title)
//                .setBody(body)
//                .build();
//    }
//}
