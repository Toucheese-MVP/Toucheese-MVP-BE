//package com.example.toucheese_be.domain.user.fcm;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.messaging.FirebaseMessaging;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FcmConfig {
//    @Value("${fcm.config}")
//    private String firebaseConfigJson;
//
//    @Bean
//    FirebaseMessaging firebaseMessaging() throws IOException {
//        // JSON 문자열을 FirebaseCredential으로 변환
//        GoogleCredentials credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(firebaseConfigJson.getBytes()));
//
//
//        FirebaseApp firebaseApp = null;
//        List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();
//
//        if (firebaseAppList != null && !firebaseAppList.isEmpty()) {
//            for (FirebaseApp app : firebaseAppList) {
//                if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
//                    firebaseApp = app;
//                }
//            }
//        } else {
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(credentials)
//                    .build();
//
//            firebaseApp = FirebaseApp.initializeApp(options);
//        }
//        assert firebaseApp != null;
//        return FirebaseMessaging.getInstance(firebaseApp);
//    }
//}
