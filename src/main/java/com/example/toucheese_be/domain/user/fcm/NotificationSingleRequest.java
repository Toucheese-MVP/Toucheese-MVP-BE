//package com.example.toucheese_be.domain.user.fcm;
//
//import com.google.firebase.messaging.Message;
//import com.google.firebase.messaging.Notification;
//import lombok.Builder;
//import lombok.NonNull;
//
//@Builder
//public record NotificationSingleRequest(
//        @NonNull String targetToken,
//        String title,
//        String body
//) implements NotificationRequest {
//
//    public static NotificationSingleRequest of(String token, String title, String body) {
//        return NotificationSingleRequest.builder()
//                .targetToken(token)
//                .title(title)
//                .body(body)
//                .build();
//    }
//
//    public Message.Builder buildMessage() {
//        return Message.builder()
//                .setToken(targetToken)
//                .setNotification(toNotification());
//    }
//
//    @Override
//    public Notification toNotification() {
//        return Notification.builder()
//                .setTitle(title)
//                .setBody(body)
//                .build();
//    }
//}
