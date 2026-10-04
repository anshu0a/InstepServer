package com.instep.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.instep.dao.res.ApiResponse;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> runtimeException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                    ApiResponse.builder()
                        .success(false)
                        .createdAt(LocalDateTime.now())
                        .message(e.getMessage())
                        .code(HttpStatus.BAD_REQUEST)
                        .build()
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> badCredentials(BadCredentialsException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                    ApiResponse.builder()
                        .success(false)
                        .createdAt(LocalDateTime.now())
                        .message("Invalid username or password")
                        .code(HttpStatus.UNAUTHORIZED)
                        .build()
                );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse> usernameNotFound(UsernameNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                    ApiResponse.builder()
                        .success(false)
                        .createdAt(LocalDateTime.now())
                        .message(e.getMessage())
                        .code(HttpStatus.UNAUTHORIZED)
                        .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> exception(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                    ApiResponse.builder()
                        .success(false)
                        .createdAt(LocalDateTime.now())
                        .message("Something went wrong")
                        .code(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build()
                );
    }
    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> routeNotFound(NoResourceFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.builder()
                        .success(false)
                        .createdAt(LocalDateTime.now())
                        .message("Route not found")
                        .code(HttpStatus.NOT_FOUND)
                        .build());
    }
}