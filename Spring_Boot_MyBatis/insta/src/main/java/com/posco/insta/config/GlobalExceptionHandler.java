package com.posco.insta.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//예외처리
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = IllegalAccessException.class) //400 error 처리
    public ResponseEntity<Map<String,Object>> IllegalAccessException
            (IllegalAccessException e){
        Map<String,Object> map = new HashMap<>();
        map.put("errMsg",e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<Map<String,Object>> ExpiredJwtException
            (ExpiredJwtException e){
        Map<String,Object> map = new HashMap<>();
        map.put("errMsg","토큰 문제 있음");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map); //401 error 처리
    }
    @ExceptionHandler(value = Exception.class) //전체 error 처리
    public ResponseEntity<Map<String,Object>> Exception
            (Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("errMsg",e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }
}
