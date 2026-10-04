package com.instep.dao.res;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
	
    private Long id;
    private String username;
    private String name;
    
    private LocalDate birthday;
    private String gender;
    private String bio;
    
    private byte[] profilePicByte;
    private String profilePicType;
    private String profilePicUrl;
    
    private LocalDateTime createdAt;

}
