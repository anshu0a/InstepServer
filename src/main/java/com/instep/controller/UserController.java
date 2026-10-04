package com.instep.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instep.dao.res.ApiResponse;
import com.instep.dao.res.UserResponse;
import com.instep.entity.User;
import com.instep.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/{username}")
	public ResponseEntity<ApiResponse> checkUserName(@PathVariable String username) {
		return ResponseEntity.ok(userService.checkUsernameOrEmail(username));
	}
	
	@GetMapping("/get/{username}")
	public ResponseEntity<User> get(@PathVariable String username) {
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}

	@GetMapping("/random")
	public ResponseEntity<List<UserResponse>> getRandomUsers(
			@RequestParam(defaultValue = "8") int limit) {
		return ResponseEntity.ok(userService.getRandomUsers(limit));
	}

	@GetMapping("/search")
	public ResponseEntity<List<UserResponse>> searchUsers(
			@RequestParam String query,
			@RequestParam(defaultValue = "6") int limit) {
		return ResponseEntity.ok(userService.searchUsers(query, limit));
	}
}