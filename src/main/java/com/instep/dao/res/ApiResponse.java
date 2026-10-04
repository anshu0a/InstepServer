package com.instep.dao.res;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	String message;
	HttpStatus code;
	boolean success;
	
	Object object;
	
	@Builder.Default
	LocalDateTime createdAt = LocalDateTime.now();
}
