package com.instep.service;

import java.util.List;

import com.instep.dao.res.ApiResponse;
import com.instep.dao.res.UserResponse;
import com.instep.entity.User;

public interface UserService {
	
	ApiResponse checkUsernameOrEmail(String identity);
	List<UserResponse> getRandomUsers(int limit);
	List<UserResponse> searchUsers(String query, int limit);
	User getUserByUsername(String username);

}
