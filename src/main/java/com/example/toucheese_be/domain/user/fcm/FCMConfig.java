//package com.example.toucheese_be.domain.user.fcm;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.messaging.FirebaseMessaging;
//import java.io.IOException;
//import javax.annotation.PostConstruct;
//import lombok.val;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//@Configuration
//public class FCMConfig {
//    private final ClassPathResource firebaseResource;
//    private final String projectId;
//
//    public FCMConfig(
//            @Value("${fcm.file_path}") String firebaseFilePath,
//            @Value("${fcm.project_id}") String projectId
//    ) {
//        this.firebaseResource = new ClassPathResource(firebaseFilePath);
//        this.projectId = projectId;
//    }
//
//
//    @PostConstruct
//    private void init() throws IOException {
//        val option = FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.fromStream(firebaseResource.getInputStream()))
//                .setProjectId(projectId)
//                .build();
//
//        if (FirebaseApp.getApps().isEmpty()) {
//            FirebaseApp.initializeApp(option);
//        }
//    }
//
//    @Bean
//    FirebaseMessaging firebaseMessaging() {
//        return FirebaseMessaging.getInstance(firebaseApp());
//    }
//
//    @Bean
//    FirebaseApp firebaseApp() {
//        return FirebaseApp.getInstance();
//    }
//}
