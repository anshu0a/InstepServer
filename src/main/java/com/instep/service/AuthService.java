package com.instep.service;

import com.instep.dao.req.LoginRequest;
import com.instep.dao.req.RegisterAprovalRequest;
import com.instep.dao.req.RegisterRequest;
import com.instep.dao.res.ApiResponse;
import com.instep.dao.res.LoginResponse;
import com.instep.dao.res.TokenResponse;

public interface AuthService {
	
	LoginResponse login(LoginRequest req);
	LoginResponse register(RegisterRequest req);
	TokenResponse getToken(String token);
	ApiResponse forgot(String username);
	ApiResponse getApproval(RegisterAprovalRequest req);
	ApiResponse checkApproval(String email);

}
