//package com.example.toucheese_be.global.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends RuntimeException{
//
//    // OrderNotFoundException 처리기
//    @ExceptionHandler(OrderNotFoundException.class)
//    public ResponseEntity<String> handlerNotFound(OrderNotFoundException e){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
//
//    @ExceptionHandler(AlreadyCancelledException.class)
//    public ResponseEntity<String> handlerAlreadyCancelled(AlreadyCancelledException e){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handlerGenericException(Exception e){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("예약 취소 중 오류가 발생했습니다.");
//    }
//}
