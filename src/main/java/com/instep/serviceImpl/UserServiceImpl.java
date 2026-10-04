package com.instep.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.instep.dao.res.ApiResponse;
import com.instep.dao.res.UserResponse;
import com.instep.entity.User;
import com.instep.repository.UserRepository;
import com.instep.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;

	@Override
	public ApiResponse checkUsernameOrEmail(String identity) {
		String what = identity.contains("@") ? "email" : "username";
		Optional<User> opt = null;
		
		if (what.equals("email")) opt = userRepo.findByEmail(identity);
		else opt = userRepo.findByUsername(identity);
		
		boolean flag = opt.isPresent();
		
		return ApiResponse.builder()
				.success(!flag)
				.message(what + (flag ? " allready exist." : " not exist."))
				.code(HttpStatus.OK)
				.build();
	}
	
	@Override
	public List<UserResponse> getRandomUsers(int limit) {

		limit = Math.min(Math.max(limit, 1), 8);

		List<User> users = userRepo.findAll();

		Collections.shuffle(users);

		return users.stream()
				.limit(limit)
				.map(this::mapToUserResponse)
				.toList();
	}

	@Override
	public List<UserResponse> searchUsers(String query, int limit) {

		limit = Math.min(Math.max(limit, 0), 6);
		if (query == null || query.isBlank() || limit == 0) {return List.of();}
		
		return userRepo.searchPeople(query.trim())
				.stream()
				.limit(limit)
				.map(this::mapToUserResponse)
				.toList();
	}
	
	@Override
	public User getUserByUsername(String username) {
	    return userRepo.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	}
	
	// ----------------------------- TODO helper method --------------------------------------

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.username(user.getUsername())
				.name(user.getName())
				.bio(user.getBio())
				.birthday(user.getBirthday())
				.gender(user.getGender())
				.profilePicType(user.getProfilePicType())
				.profilePicByte(user.getProfilePicByte())
				.profilePicUrl(user.getProfilePicUrl())
				.build();
	}
}