package com.instep.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instep.dao.req.LoginRequest;
import com.instep.dao.req.RegisterAprovalRequest;
import com.instep.dao.req.RegisterRequest;
import com.instep.dao.res.ApiResponse;
import com.instep.dao.res.LoginResponse;
import com.instep.dao.res.TokenResponse;
import com.instep.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/ok")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("all good");
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("all good health");
    }
//    ----------------------------------- Useful ------------------------------------

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }
    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getToken(@Valid @RequestBody String token) {
        return ResponseEntity.ok(authService.getToken(token));
    }
    
    @GetMapping("/forgot/{username}")
    public ResponseEntity<ApiResponse> forgot(@PathVariable String username) {
        return ResponseEntity.ok(authService.forgot(username));
    }
    
    @PostMapping("/aproval")
    public ResponseEntity<ApiResponse> askAproval(@Valid @RequestBody RegisterAprovalRequest req ) {
        return ResponseEntity.ok(authService.getApproval(req));
    }
    
    @GetMapping("/checkaproval")
    public ResponseEntity<ApiResponse> checkAproval(@RequestParam String email) {
    	return ResponseEntity.ok(authService.checkApproval(email));
    }
    
}


















