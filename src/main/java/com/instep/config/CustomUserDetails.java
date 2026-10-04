package com.instep.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.instep.entity.User;
import com.instep.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {
	
	private final UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user  = null;
		if(username.contains("@") && username.contains(".")) 
			user = userRepo.findByEmail(username)
			.orElseThrow(()-> new UsernameNotFoundException("User not found with Email: "+username));
		else 
			user = userRepo.findByUsername(username)
			.orElseThrow(()-> new UsernameNotFoundException("User not found with Username: "+username));
		
		return user;
	}

}
